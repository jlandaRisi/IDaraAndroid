package com.risitech.data.domain.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/**
 * Clase base que representa un modelo genérico en la base de datos.
 * Hereda de RealmObject para ser gestionado por Realm.
 */
open class BaseRealmModel: RealmObject {

    /**
     * Identificador único del modelo (clave primaria).
     */
    @PrimaryKey
    var id: ObjectId = ObjectId()

    /**
     * Indica si el modelo está eliminado lógicamente.
     */
    var deleted: Boolean = false
}