package com.picpay.desafio.core.data.utils.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DEFAULT_DISPATCHER = "default"
const val IO_DISPATCHER = "io"
const val MAIN_DISPATCHER = "main"

object DispatcherDI {
    val module = module {
        factory(named(DEFAULT_DISPATCHER)) { Dispatchers.Default }
        factory(named(IO_DISPATCHER)) { Dispatchers.IO }
        factory(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    }
}