package com.risitech.data.domain.datasources

import com.risitech.data.domain.models.BaseRealmModel

/**
 * Interfaz que define las operaciones básicas (CRUD) para interactuar con una fuente de datos.
 * @param T Tipo del modelo que extiende de BaseRealmModel.
 */
interface AppDataSource<T: BaseRealmModel> {

    /**
     * Inserta un modelo en la fuente de datos.
     * @param model Modelo a insertar.
     */
    fun insert(model: T)

    /**
     * Actualiza un modelo existente en la fuente de datos.
     * @param model Modelo con los datos actualizados.
     */
    fun update(model: T)

    /**
     * Recupera todos los modelos de la fuente de datos.
     * @return Lista de modelos.
     */
    fun getAll(): List<T>

    /**
     * Elimina un modelo por su ID.
     * @param id Identificador del modelo a eliminar.
     * @param hardDelete Indica si la eliminación es física (true) o lógica (false).
     */
    fun deleteById(id: String, hardDelete: Boolean = false)

    /**
     * Elimina todos los modelos de la fuente de datos.
     * @param hardDelete Indica si la eliminación es física (true) o lógica (false).
     */
    fun deleteAll(hardDelete: Boolean = false)
}