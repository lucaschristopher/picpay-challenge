package com.picpay.desafio.android.data.datasource.remote

import com.picpay.desafio.android.data.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface PicPayRemoteDataSource {
    suspend fun getUsers(): Flow<List<UserResponse>>
}