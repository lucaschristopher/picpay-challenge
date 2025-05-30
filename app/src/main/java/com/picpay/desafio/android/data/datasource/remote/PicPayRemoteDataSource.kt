package com.picpay.desafio.android.data.datasource.remote

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.core.data.api.safeApiCall

internal fun interface PicPayRemoteDataSource {
    suspend fun getUsers(): Result<List<UserResponse>>
}

internal class PicPayRemoteDataSourceImpl(
    private val service: PicPayService
) : PicPayRemoteDataSource {

    override suspend fun getUsers(): Result<List<UserResponse>> = safeApiCall {
        service.getUsers()
    }
}