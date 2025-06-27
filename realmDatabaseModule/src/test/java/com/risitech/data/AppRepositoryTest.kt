package com.risitech.data

import com.risitech.data.domain.datasources.AppDataSource
import com.risitech.data.infrastructure.repositories.AppRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import io.mockk.*
import junit.framework.TestCase.assertEquals

class AppRepositoryTest {

    private lateinit var appDataSource: AppDataSource<UserModelTest>
    private lateinit var appRepository: AppRepository<UserModelTest>

    @Before
    fun setup() {
        appDataSource = mockk()
        appRepository = AppRepository(appDataSource)
    }

    @Test
    fun `insert should call datasource insert`() = runBlocking {
        val model = UserModelTest().apply {
            id = ObjectId()
            name = "Rodrigo"
            description = "Prueba de datos"
        }
        coEvery { appDataSource.insert(model) } just Runs
        appRepository.insert(model)
        coVerify { appDataSource.insert(model) }
    }

    @Test
    fun `update should call datasource update`() = runBlocking {
        val model = UserModelTest().apply {
            id = ObjectId()
            name = "Francisco Landa"
            description = "Actualizado"
        }
        coEvery { appDataSource.update(model) } just Runs
        appRepository.update(model)
        coVerify { appDataSource.update(model) }
    }

    @Test
    fun `getAll should return list of models from datasource`() = runBlocking {
        val models = listOf(
            UserModelTest().apply { id = ObjectId(); name = "Miku" },
            UserModelTest().apply { id = ObjectId(); name = "Pyra" }
        )
        coEvery { appDataSource.getAll() } returns models
        val result = appRepository.getAll()
        assertEquals(models, result)
        coVerify { appDataSource.getAll() }
    }

    @Test
    fun `deleteById should call datasource deleteById`() = runBlocking {
        val id = ObjectId().toHexString()
        val hardDelete = true
        coEvery { appDataSource.deleteById(id, hardDelete) } just Runs
        appRepository.deleteById(id, hardDelete)
        coVerify { appDataSource.deleteById(id, hardDelete) }
    }

    @Test
    fun `deleteAll should call datasource deleteAll`() = runBlocking {
        val hardDelete = false
        coEvery { appDataSource.deleteAll(hardDelete) } just Runs
        appRepository.deleteAll(hardDelete)
        coVerify { appDataSource.deleteAll(hardDelete) }
    }
}