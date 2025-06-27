package com.risitech.data.domain.repositories

/**
 * Interfaz que define las operaciones básicas (CRUD) para interactuar con la capa de datos desde la lógica de negocio.
 * @param T Tipo del modelo que será gestionado.
 */
interface DataRepository<T> {

    /**
     * Inserta un modelo en la base de datos.
     * @param model Modelo a insertar.
     */
    suspend fun insert(model: T)

    /**
     * Actualiza un modelo existente en la base de datos.
     * @param model Modelo con los datos actualizados.
     */
    suspend fun update(model: T)

    /**
     * Recupera todos los modelos de la base de datos.
     * @return Lista de modelos.
     */
    suspend fun getAll(): List<T>

    /**
     * Elimina un modelo por su ID.
     * @param id Identificador del modelo a eliminar.
     * @param hardDelete Indica si la eliminación es física (true) o lógica (false).
     */
    suspend fun deleteById(id: String, hardDelete: Boolean = false)

    /**
     * Elimina todos los modelos de la base de datos.
     * @param hardDelete Indica si la eliminación es física (true) o lógica (false).
     */
    suspend fun deleteAll(hardDelete: Boolean = false)
}