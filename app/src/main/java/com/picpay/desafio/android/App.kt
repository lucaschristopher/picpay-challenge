package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.di.*
import com.picpay.desafio.android.domain.di.DomainModule
import com.picpay.desafio.android.domain.di.useCaseModule
import com.picpay.desafio.android.presentation.di.PresentationModule
import com.picpay.desafio.android.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}