package com.picpay.desafio.android.data.datasource.local

import com.picpay.desafio.android.data.datasource.local.model.UserEntity

interface PicPayLocalDataSource {
    suspend fun getUsers(): List<UserEntity>
}