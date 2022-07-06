package com.picpay.desafio.android

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 * MainDIComponentTest: makes the function of the app class, responsible for
 * starting the app and loading the module in the context of tests.
 */

/**
 * This function initializes Koin and its modules for testing purposes only.
 */

const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

fun configureTestAppComponent() = startKoin {
    loadKoinModules(
        configureDataModuleForTest(BASE_URL) +
                configureDomainModuleForTest()
    )
}
