package com.picpay.desafio.android.data.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSourceImpl
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSourceImpl
import com.picpay.desafio.android.data.service.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    // Exposes the modules to be loaded in the App
    fun load() {
        loadKoinModules(networkModules() + repositoryModule())
    }

    // Koin module to provide some items of interest to us
    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BASIC

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<PicPayService>(get(), get())
            }
        }
    }

    // // Koin module to provide some items of interest to us
    private fun repositoryModule(): Module {

        return module {
            single<PicPayRemoteDataSource> { PicPayRemoteDataSourceImpl(get()) }
            single<PicPayLocalDataSource> { PicPayLocalDataSourceImpl(get()) }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}