package com.picpay.desafio.android.data.service

import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.http.GET

internal fun interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}