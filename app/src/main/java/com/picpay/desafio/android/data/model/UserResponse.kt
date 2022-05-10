package com.picpay.desafio.android.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("img") val imgUser: String,
    @SerializedName("name") val nameUser: String,
    @SerializedName("id") val idUser: Int,
    @SerializedName("username") val usernameUser: String
)