package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.common.getKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@PicPayApplication)
            modules(getKoinModules())
        }
    }
}