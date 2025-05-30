package com.picpay.desafio.android.presentation.viewmodel

import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.mock.userModelList
import com.picpay.desafio.android.presentation.home.mvi.PicPayAction
import com.picpay.desafio.android.presentation.home.mvi.PicPayResult
import com.picpay.desafio.android.presentation.home.viewmodel.PicPayViewModel
import com.picpay.desafio.core.common.test.BaseUnitTest
import com.picpay.desafio.core.common.test.MainDispatcherRule
import com.picpay.desafio.core.common.test.observeEffect
import com.picpay.desafio.core.data.network.helper.NetworkHelper
import com.picpay.desafio.core.presentation.model.State
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class PicPayViewModelTest : BaseUnitTest() {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<GetUsersUseCase>()
    private val networkHelper = mockk<NetworkHelper>()
    private lateinit var viewModel: PicPayViewModel

    @Before
    fun setup() {
        viewModel = PicPayViewModel(useCase, networkHelper)
    }

    @Test
    fun `dispatch GetUsers triggers loading and success state`() = runTest {
        // Given
        val users = userModelList
        coEvery { networkHelper.isConnected() } returns true
        coEvery { useCase.invoke(isConnected = true) } returns flowOf(State.Success(users))

        // When
        viewModel.dispatch(PicPayAction.GetUsers)

        // Then
        val state = viewModel.uiState.first { !it.isLoading }
        assertEquals(users, state.users)
    }

    @Test
    fun `dispatch GetUsers triggers offline toast when not connected`() = runTest {
        // Given
        val users = userModelList
        val observer = viewModel.observeEffect()

        coEvery { networkHelper.isConnected() } returns false
        coEvery { useCase.invoke(isConnected = false) } returns flowOf(State.Success(users))

        // When
        viewModel.dispatch(PicPayAction.GetUsers)

        // Then
        assertEquals(PicPayResult.ShowOfflineToast, observer.replayCache.last())
    }
}