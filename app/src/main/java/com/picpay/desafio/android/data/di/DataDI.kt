package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSourceImpl
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSourceImpl
import com.picpay.desafio.android.data.repository.PicPayRepositoryImpl
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.core.data.network.factory.ApiFactory
import com.picpay.desafio.core.data.utils.di.IO_DISPATCHER
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataDI {
    val module = module {
        factory { ApiFactory.build(retrofit = get(), apiClass = PicPayService::class.java) }

        factory<PicPayRepository> {
            PicPayRepositoryImpl(
                remoteDataSource = get(),
                localDataSource = get(),
                dispatcher = get(named(IO_DISPATCHER))
            )
        }

        factory<PicPayRemoteDataSource> {
            PicPayRemoteDataSourceImpl(service = get())
        }

        factory<PicPayLocalDataSource> { PicPayLocalDataSourceImpl(dao = get()) }
    }
}
