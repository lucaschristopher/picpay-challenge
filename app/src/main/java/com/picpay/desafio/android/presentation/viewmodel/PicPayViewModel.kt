package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.mapper.PresentationMapper
import com.picpay.desafio.android.core.state.RequestState
import com.picpay.desafio.android.core.util.Constants
import com.picpay.desafio.android.domain.GetPicPayUsersUserCase
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.model.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PicPayViewModel(
    private val useCase: GetPicPayUsersUserCase
) : ViewModel() {

    private val _users = MutableStateFlow<RequestState<List<UserState>>>(
        RequestState.Initial
    )
    val users: StateFlow<RequestState<List<UserState>>>
        get() = _users

    fun init() {
        getPicPayUsers()
    }

    private fun getPicPayUsers() {
        viewModelScope.launch {
            useCase.invoke()
                .onStart {
                    _users.value = RequestState.Loading
                }
                .catch {
                    _users.value = RequestState.Error(Constants.ERROR_HTTP_MESSAGE)
                }
                .collect(::handleResponse)
        }
    }

    private fun handleResponse(list: List<User>?) {
        _users.value = if (list == null) {
            RequestState.Error(Constants.ERROR_NOT_USERS_MESSAGE)
        } else {
            RequestState.Success(PresentationMapper().mapList(list))
        }
    }
}
