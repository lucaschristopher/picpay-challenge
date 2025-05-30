package com.picpay.desafio.android.presentation.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.presentation.home.mvi.PicPayAction
import com.picpay.desafio.android.presentation.home.mvi.PicPayViewState
import com.picpay.desafio.core.data.network.helper.NetworkHelper
import com.picpay.desafio.core.presentation.model.State
import com.picpay.desafio.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

internal class PicPayViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val networkHelper: NetworkHelper,
) : BaseViewModel<PicPayAction, Unit, PicPayViewState>() {

    override val initialState: PicPayViewState
        get() = PicPayViewState()

    override fun dispatch(actionEvent: PicPayAction) {
        when (actionEvent) {
            is PicPayAction.GetUsers -> getUsers()
            is PicPayAction.OnRefresh -> {
                updateUiState { copy(isRefreshing = true) }
                getUsers()
            }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true, exception = null) }
            val isConnected = networkHelper.isConnected()
            getUsersUseCase.invoke(isConnected).collect { state ->
                when (state) {
                    is State.Success -> updateUiState {
                        copy(isLoading = false, isRefreshing = false, users = state.result)
                    }

                    is State.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            isRefreshing = false,
                            hasError = true,
                            exception = state.throwable as? Exception
                        )
                    }

                    State.Loading -> updateUiState {
                        copy(isLoading = true)
                    }

                    State.Idle -> Unit
                }
            }
        }
    }
}