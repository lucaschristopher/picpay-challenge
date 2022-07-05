package com.picpay.desafio.android.data.service

import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.http.GET

interface PicPayService {

    @GET("useras")
    suspend fun getUsers(): List<UserResponse>
}