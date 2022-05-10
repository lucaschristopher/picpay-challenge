package com.picpay.desafio.android.core

import com.picpay.desafio.android.presentation.model.UserUiModel

sealed class AppState {
    object Loading : AppState()
    data class Success(val list: List<UserUiModel>) : AppState()
    data class Error(val error: Throwable) : AppState()
}