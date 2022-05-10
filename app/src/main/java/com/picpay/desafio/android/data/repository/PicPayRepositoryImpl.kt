package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

class PicPayRepositoryImpl(
    private val picPayRemoteDataSource: PicPayRemoteDataSource,
    private val picPayLocalDataSource: PicPayLocalDataSource,
) : PicPayRepository {

    override suspend fun getUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}