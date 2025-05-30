package com.picpay.desafio.core.presentation.model

sealed class State<out T> {
    data object Idle : State<Nothing>()
    data object Loading : State<Nothing>()
    data class Success<T>(val result: T) : State<T>()
    data class Error(val throwable: Throwable? = null) : State<Nothing>()
}