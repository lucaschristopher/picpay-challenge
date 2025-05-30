package com.picpay.desafio.core.data.network.observer

import kotlinx.coroutines.flow.Flow

fun interface ConnectivityObserver {

    enum class Status {
        Available, Unavailable, Losing, Lost
    }

    fun observe(): Flow<Status>
}