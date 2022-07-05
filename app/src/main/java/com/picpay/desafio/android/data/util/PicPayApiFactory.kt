package com.picpay.desafio.android.data.util

import retrofit2.Retrofit

object PicPayApiFactory {

    fun <T> build(retrofit: Retrofit, apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }
}
