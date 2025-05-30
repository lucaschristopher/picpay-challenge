package com.picpay.desafio.core.data.network.factory

import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

object ConverterFactory {

    fun provide(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}