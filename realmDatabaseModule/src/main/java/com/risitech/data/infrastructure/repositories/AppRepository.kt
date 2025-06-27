package com.risitech.data.infrastructure.repositories

import com.risitech.data.domain.datasources.AppDataSource
import com.risitech.data.domain.models.BaseRealmModel
import com.risitech.data.domain.repositories.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository<T: BaseRealmModel>(private val datasource: AppDataSource<T>): DataRepository<T> {
    override suspend fun insert(model: T) {
        withContext(Dispatchers.IO) {
            datasource.insert(model)
        }
    }

    override suspend fun update(model: T) {
        withContext(Dispatchers.IO) {
            datasource.update(model)
        }
    }

    override suspend fun getAll(): List<T> {
        return withContext(Dispatchers.IO) {
            datasource.getAll()
        }
    }

    override suspend fun deleteById(id: String, hardDelete: Boolean) {
        withContext(Dispatchers.IO) {
            datasource.deleteById(id, hardDelete)
        }
    }

    override suspend fun deleteAll(hardDelete: Boolean) {
        withContext(Dispatchers.IO) {
            datasource.deleteAll(hardDelete)
        }
    }
}