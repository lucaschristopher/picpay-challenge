package com.picpay.desafio.core.data.network.di

import com.picpay.desafio.core.BuildConfig
import com.picpay.desafio.core.data.network.factory.ConverterFactory
import com.picpay.desafio.core.data.network.factory.OkHttpClientFactory
import com.picpay.desafio.core.data.network.helper.NetworkHelper
import com.picpay.desafio.core.data.network.helper.NetworkHelperImpl
import com.picpay.desafio.core.data.utils.di.IO_DISPATCHER
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit

object NetworkDI {

    val module = module {
        single<OkHttpClient> { OkHttpClientFactory.build() }
        single<Converter.Factory> { ConverterFactory.provide() }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(get<OkHttpClient>())
                .addConverterFactory(get<Converter.Factory>())
                .build()
        }

        factory<NetworkHelper> {
            NetworkHelperImpl(
                context = get(),
                dispatcher = get(named(IO_DISPATCHER))
            )
        }
    }
}