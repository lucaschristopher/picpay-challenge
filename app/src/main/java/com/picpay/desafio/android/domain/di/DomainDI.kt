package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.domain.usecase.GetUsersUseCaseImpl
import org.koin.dsl.module

object DomainDI {

    val module = module {
        factory<GetUsersUseCase> { GetUsersUseCaseImpl(repository = get()) }
    }
}