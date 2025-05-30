package com.picpay.desafio.android.data.datasource.remote

import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.mock.userResponseList
import com.picpay.desafio.core.common.test.BaseUnitTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PicPayRemoteDataSourceTest : BaseUnitTest() {

    private val service = mockk<PicPayService>()
    private lateinit var dataSource: PicPayRemoteDataSource

    @Before
    fun setup() {
        dataSource = PicPayRemoteDataSourceImpl(service)
    }

    @Test
    fun `getUsers should return Result success when service succeeds`() = runTest {
        // Given
        val mockResponse = userResponseList
        coEvery { service.getUsers() } returns userResponseList

        // When
        val result = dataSource.getUsers()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockResponse, result.getOrNull())
    }

    @Test
    fun `getUsers should return Result failure when service throws`() = runTest {
        // Given
        val mockException = IOException("Network error")
        coEvery { service.getUsers() } throws mockException

        // When
        val result = dataSource.getUsers()

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IOException)
    }
}