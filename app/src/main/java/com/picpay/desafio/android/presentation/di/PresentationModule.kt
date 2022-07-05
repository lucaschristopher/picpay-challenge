package com.picpay.desafio.android.presentation.di

import com.picpay.desafio.android.presentation.viewmodel.PicPayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PicPayViewModel(useCase = get())
    }
}

object PresentationModule {
    fun load() {
        loadKoinModules(viewModelModule)
    }
}
