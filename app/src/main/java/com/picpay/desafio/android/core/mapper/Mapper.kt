package com.picpay.desafio.android.core.mapper

interface Mapper<I, O> {
    fun mapList(input: I): O
}