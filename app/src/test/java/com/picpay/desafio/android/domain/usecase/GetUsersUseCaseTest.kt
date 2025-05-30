package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.android.mock.userModelList
import com.picpay.desafio.core.common.test.BaseUnitTest
import com.picpay.desafio.core.presentation.model.State
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetUsersUseCaseTest : BaseUnitTest() {

    private val repository = mockk<PicPayRepository>()
    private lateinit var useCase: GetUsersUseCase

    @Before
    fun setup() {
        useCase = GetUsersUseCaseImpl(repository)
    }

    @Test
    fun `invoke emits success when repository returns success`() = runTest {
        // Given
        val users = userModelList
        coEvery { repository.getUsers(isConnected = true) } returns Result.success(users)

        // When
        val emissions = useCase.invoke(isConnected = true).toList()

        // Then
        assertTrue(emissions.first() is State.Success)
        assertEquals(users, (emissions.first() as State.Success).result)
    }

    @Test
    fun `invoke emits error when repository returns failure`() = runTest {
        // Given
        val exception = RuntimeException("Viiiish")
        coEvery { repository.getUsers(isConnected = true) } returns Result.failure(exception)

        // When
        val emissions = useCase.invoke(isConnected = true).toList()

        // Then
        assertTrue(emissions.first() is State.Error)
        assertEquals(exception, (emissions.first() as State.Error).throwable)
    }
}