package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.util.Constants
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetPicPayUsersUseCase
import com.picpay.desafio.android.presentation.model.UserState
import com.picpay.desafio.android.presentation.state.State
import com.picpay.desafio.android.presentation.util.PresentationMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PicPayViewModel(
    private val useCase: GetPicPayUsersUseCase
) : ViewModel() {

    private val _users = MutableStateFlow<State<List<UserState>>>(
        State.Initial
    )
    val users: StateFlow<State<List<UserState>>>
        get() = _users

    fun init() {
        getPicPayUsers()
    }

    private fun getPicPayUsers() {
        viewModelScope.launch {
            useCase()
                .onStart {
                    handleLoading()
                }
                .catch {
                    handleError()
                }
                .collect(::handleResponse)
        }
    }

    // region Handlers
    private fun handleLoading() {
        _users.value = State.Loading
    }

    private fun handleError() {
        _users.value = State.Error(Constants.ERROR_HTTP_MESSAGE)
    }

    private fun handleResponse(list: List<User>) {
        _users.value = State.Success(PresentationMapper().mapList(list))
    }
    //endregion
}
