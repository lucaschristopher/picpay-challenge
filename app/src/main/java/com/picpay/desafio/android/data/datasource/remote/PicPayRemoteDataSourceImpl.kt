package com.picpay.desafio.android.data.datasource.remote

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.data.service.PicPayService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PicPayRemoteDataSourceImpl(
    private val service: PicPayService
) : PicPayRemoteDataSource {

    override suspend fun getUsers(): Flow<List<UserResponse>> = flow {
        val response = service.getUsers()
        emit(response)
    }
}
