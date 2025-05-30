package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.mapper.toModel
import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.android.mock.userEntityList
import com.picpay.desafio.android.mock.userResponseList
import com.picpay.desafio.core.common.test.BaseUnitTest
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PicPayRepositoryTest : BaseUnitTest() {

    private val remote = mockk<PicPayRemoteDataSource>()
    private val local = mockk<PicPayLocalDataSource>()
    private val dispatcher = StandardTestDispatcher()
    private lateinit var repository: PicPayRepository

    @Before
    fun setup() {
        repository = PicPayRepositoryImpl(remote, local, dispatcher)
    }

    @Test
    fun `getUsers fetches remote and cache locally when connected`() = runTest(dispatcher) {
        // Given
        val remoteUsers = userResponseList
        coEvery { remote.getUsers() } returns Result.success(remoteUsers)
        coEvery { local.insertUsers(any()) } just Runs

        // When
        val result = repository.getUsers(isConnected = true)

        // Then
        assertTrue(result.isSuccess)
        coVerify { local.insertUsers(any()) }
    }

    @Test
    fun `getUsers returns local cache when not connected`() = runTest(dispatcher) {
        // Given
        val localUsers = userEntityList
        coEvery { local.getUsers() } returns localUsers

        // When
        val result = repository.getUsers(isConnected = false)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(localUsers.map { it.toModel() }, result.getOrNull())
    }
}