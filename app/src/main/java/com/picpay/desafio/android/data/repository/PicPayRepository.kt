package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface PicPayRepository {
    suspend fun getUsers(): Flow<List<User>>
}