package com.picpay.desafio.android

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.data.util.OkHttpClientFactory
import com.picpay.desafio.android.data.util.PicPayApiFactory
import com.picpay.desafio.android.data.util.RetrofitFactory
import com.picpay.desafio.android.domain.usecase.GetPicPayUsersUseCase
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

/**
 * #### Automated tests ####
 * This file is a mirror of what we did with our modules...
 * Here, we organize the IDs for tests using Koin and application classes.
 * We'll think about data layer and domain.
 */
//
//fun configureDomainModuleForTest() = module {
//    factory { GetPicPayUsersUseCase(repository = get()) }
//}
//
//fun configureDataModuleForTest(baseUrl: String) = module {
//
//    // Service
//    single {
//        RetrofitFactory.build(
//            url = baseUrl,
//            factory = GsonConverterFactory.create(GsonBuilder().create()),
//            client = OkHttpClientFactory.build()
//        )
//    }
//
//    factory {
//        PicPayApiFactory.build(
//            retrofit = get(),
//            apiClass = PicPayService::class.java
//        )
//    }
//}
