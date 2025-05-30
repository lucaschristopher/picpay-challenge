package com.picpay.desafio.android.presentation.di

import com.picpay.desafio.android.presentation.home.viewmodel.PicPayViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object PresentationDI {

    val module = module {
        viewModel {
            PicPayViewModel(getUsersUseCase = get(), networkHelper = get())
        }
    }
}