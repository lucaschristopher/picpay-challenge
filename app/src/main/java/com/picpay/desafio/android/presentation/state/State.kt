package com.picpay.desafio.android.presentation.state

sealed class State<out T> {
    object Initial : State<Nothing>()
    object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Error<T>(val message: String, val data: T? = null) : State<T>()
}
