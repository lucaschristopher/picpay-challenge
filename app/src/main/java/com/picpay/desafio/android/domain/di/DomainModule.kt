package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.GetPicPayUsers
import com.picpay.desafio.android.domain.GetPicPayUsersUserCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    // Exposes the modules to be loaded in the App
    fun load() {
        loadKoinModules(useCaseModule())
    }

    // Koin module to provide our Use Cases
    private fun useCaseModule(): Module {
        return module {
            single<GetPicPayUsersUserCase> {
                GetPicPayUsers(get())
            }
        }
    }
}