package com.picpay.desafio.android.data.model

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "img")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "username")
    val username: String,
)
