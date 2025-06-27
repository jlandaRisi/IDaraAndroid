package com.risitech.data.infrastructure.datasources

import com.risitech.data.domain.datasources.AppDataSource
import com.risitech.data.domain.models.BaseRealmModel
import kotlin.reflect.KClass

/**
 * Clase que implementa las operaciones CRUD para interactuar con la base de datos Realm.
 * @param T Tipo del modelo que extiende de BaseRealmModel.
 * @property clazz Clase del modelo que será gestionado.
 */
class LocalDataSource<T: BaseRealmModel>(private val clazz: KClass<T>): AppDataSource<T> {

    /**
     * Inserta un modelo en la base de datos Realm.
     * @param model Modelo a insertar.
     */
    override fun insert(model: T) {
        RealmModelManager.realm.writeBlocking {
            copyToRealm(model)
        }
    }

    /**
     * Actualiza un modelo existente en la base de datos Realm.
     * @param model Modelo con los datos actualizados.
     */
    override fun update(model: T) {
        RealmModelManager.realm.writeBlocking {
            val item = query(clazz, "id == $0", model.id).first().find()
            item?.let {
                // Iterar sobre todas las propiedades del modelo usando reflexión
                model::class.members.filterIsInstance<kotlin.reflect.KMutableProperty<*>>()
                    .forEach { property ->
                        val newValue = property.getter.call(model)
                        val targetProperty =
                            it::class.members.find { it.name == property.name } as? kotlin.reflect.KMutableProperty<*>
                        targetProperty?.setter?.call(it, newValue)
                    }
            }
        }
    }

    /**
     * Recupera todos los modelos de la base de datos Realm.
     * @return Lista de modelos.
     */
    override fun getAll(): List<T> {
        val result = ArrayList<T>()
        val items = RealmModelManager.realm.query(clazz).find()
        result.addAll(items)
        return result
    }

    /**
     * Elimina un modelo por su ID de la base de datos Realm.
     * @param id Identificador del modelo a eliminar.
     * @param hardDelete Indica si la eliminación es física (true) o lógica (false).
     */
    override fun deleteById(id: String, hardDelete: Boolean) {
        RealmModelManager.realm.writeBlocking {
            val item = query(clazz, "id == $0", id).first().find()
            item?.let {
                if (hardDelete) {
                    delete(item)
                } else {
                    item.deleted = true
                }
            }
        }
    }

    /**
     * Elimina todos los modelos de la base de datos Realm.
     * @param hardDelete Indica si la eliminación es física (true) o lógica (false).
     */
    override fun deleteAll(hardDelete: Boolean) {
        RealmModelManager.realm.writeBlocking {
            val items = query(clazz).find()
            if (hardDelete) {
                delete(items)
            } else {
                items.forEach { it.deleted = true }
            }
        }
    }
}