package com.picpay.desafio.android.core.state

sealed class RequestState<out T> {
    object Initial : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error<T>(val message: String, val data: T? = null) : RequestState<T>()
}
