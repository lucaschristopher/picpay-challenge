package com.picpay.desafio.android.core

import retrofit2.Response
import java.net.HttpURLConnection

sealed class OutputState<out Response> {
    data class Success<Response>(val value: Response) : OutputState<Response>()
    data class Failure(val statusCode: Int) : OutputState<Nothing>()
}

fun <T : Any> Response<T>.parseResponse(): OutputState<T> {
    if (isSuccessful) {
        val body = body()
        if (body != null) return OutputState.Success(body)
    } else {
        return OutputState.Failure(code())
    }

    return OutputState.Failure(HttpURLConnection.HTTP_INTERNAL_ERROR)
}