package com.picpay.desafio.android

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.core.util.Constants
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
import com.picpay.desafio.android.domain.usecase.GetPicPayUsersUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

/**
 * #### Automated tests ####
 * This file is a mirror of what we did with our modules...
 * Here, we organize the IDs for tests using Koin and application classes.
 * We'll think about data layer and domain.
 */

fun configureDomainModuleForTest() = module {
    factory { GetPicPayUsersUseCase(repository = get()) }
}

fun configureDataModuleForTest(baseUrl: String) = module {

    single {
        RetrofitFactory.build(
            url = baseUrl,
            factory = GsonConverterFactory.create(GsonBuilder().create()),
            client = OkHttpClientFactory.build()
        )
    }

    factory {
        PicPayApiFactory.build(
            retrofit = get(),
            apiClass = PicPayService::class.java
        )
    }

    single<PicPayRepository> {
        PicPayRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            dispatcher = get(named(Constants.IO_DISPATCHER))
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

    single { AppDatabase.getDatabase(androidContext()).userDao }

    factory(named(Constants.IO_DISPATCHER)) { Dispatchers.IO }
}
