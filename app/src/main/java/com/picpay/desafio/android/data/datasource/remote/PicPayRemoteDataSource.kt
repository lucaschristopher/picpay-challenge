package com.picpay.desafio.android.data.datasource.remote

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface PicPayRemoteDataSource {

    suspend fun getUsers(): Flow<List<User>?>
}