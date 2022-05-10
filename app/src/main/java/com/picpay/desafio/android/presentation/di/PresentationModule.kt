package com.picpay.desafio.android.presentation.di

import com.picpay.desafio.android.presentation.viewmodel.PicPayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    // Koin module to provide our ViewModel
    private fun viewModelModule(): Module {
        return module {
            viewModel {
                PicPayViewModel(get())
            }
        }
    }
}