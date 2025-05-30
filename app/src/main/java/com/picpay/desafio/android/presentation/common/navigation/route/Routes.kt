package com.picpay.desafio.android.presentation.common.navigation.route

import kotlinx.serialization.Serializable

@Serializable
internal sealed class Route {

    @Serializable
    data object Home : Route()
}