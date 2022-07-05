package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.usecase.GetPicPayUsers
import com.picpay.desafio.android.domain.usecase.GetPicPayUsersUserCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetPicPayUsersUserCase> {
        GetPicPayUsers(repository = get())
    }
}

object DomainModule {
    fun load() {
        loadKoinModules(useCaseModule)
    }
}
