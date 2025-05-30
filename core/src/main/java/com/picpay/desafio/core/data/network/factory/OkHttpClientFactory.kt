package com.picpay.desafio.core.data.network.factory

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIME_VALUE: Long = 60

internal object OkHttpClientFactory {

    fun build(
        interceptors: List<Interceptor> = emptyList(),
        networkInterceptors: List<Interceptor> = emptyList(),
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .setupTimeout()
            .apply {
                interceptors.forEach { addInterceptor(it) }
                networkInterceptors.forEach { addNetworkInterceptor(it) }
            }
            .build()
    }

    private fun OkHttpClient.Builder.setupTimeout() = apply {
        readTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
        writeTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
    }
}