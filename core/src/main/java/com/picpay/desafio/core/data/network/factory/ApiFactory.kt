package com.picpay.desafio.core.data.network.factory

import retrofit2.Retrofit

object ApiFactory {

    fun <T> build(retrofit: Retrofit, apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }
}