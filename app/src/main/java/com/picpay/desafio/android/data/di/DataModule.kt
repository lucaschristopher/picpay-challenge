package com.picpay.desafio.android.data.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.core.util.Constants.Companion.DEFAULT_DISPATCHER
import com.picpay.desafio.android.core.util.Constants.Companion.DEFAULT_SCOPE
import com.picpay.desafio.android.core.util.Constants.Companion.IO_DISPATCHER
import com.picpay.desafio.android.core.util.Constants.Companion.MAIN_DISPATCHER
import com.picpay.desafio.android.data.datasource.local.AppDatabase
import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSourceImpl
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSourceImpl
import com.picpay.desafio.android.data.repository.PicPayRepository
import com.picpay.desafio.android.data.repository.PicPayRepositoryImpl
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.data.util.OkHttpClientFactory
import com.picpay.desafio.android.data.util.PicPayApiFactory
import com.picpay.desafio.android.data.util.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    factory { PicPayApiFactory.build(retrofit = get(), apiClass = PicPayService::class.java) }
}

val networkModule = module {
    single {
        OkHttpClientFactory.build()
    }

    single {
        GsonConverterFactory.create(GsonBuilder().create())
    }

    single {
        RetrofitFactory.build(url = BuildConfig.BASE_URL, client = get(), factory = get())
    }
}

val repositoryModule = module {
    single<PicPayRepository> {
        PicPayRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }

    single<PicPayRemoteDataSource> {
        PicPayRemoteDataSourceImpl(
            service = get()
        )
    }

    single<PicPayLocalDataSource> {
        PicPayLocalDataSourceImpl(
            dao = get()
        )
    }
}

val daoModule = module {
    single { AppDatabase.getDatabase(androidContext()).userDao }
}

val dispatcherModule = module {
    factory(named(DEFAULT_DISPATCHER)) { Dispatchers.Default }
    factory(named(IO_DISPATCHER)) { Dispatchers.IO }
    factory(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    factory(named(DEFAULT_SCOPE)) { CoroutineScope(Dispatchers.Default) }
}

object DataModule {

    fun load() {
        loadKoinModules(
            networkModule + apiModule + repositoryModule + daoModule + dispatcherModule
        )
    }
}