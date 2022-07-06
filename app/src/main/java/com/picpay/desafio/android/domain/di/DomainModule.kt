package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.usecase.GetPicPayUsersUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetPicPayUsersUseCase(repository = get())
    }
}

object DomainModule {
    fun load() {
        loadKoinModules(useCaseModule)
    }
}
