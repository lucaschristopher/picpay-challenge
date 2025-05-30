package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.UserModel

internal fun interface PicPayRepository {
    suspend fun getUsers(isConnected: Boolean): Result<List<UserModel>>
}