package com.picpay.desafio.android.data.datasource.local

import com.picpay.desafio.core.data.database.dao.UserDao
import com.picpay.desafio.core.data.database.model.UserEntity

interface PicPayLocalDataSource {
    suspend fun getUsers(): List<UserEntity>
    suspend fun insertUsers(users: List<UserEntity>)
}

class PicPayLocalDataSourceImpl(
    private val dao: UserDao
) : PicPayLocalDataSource {

    override suspend fun getUsers(): List<UserEntity> = dao.getAll()
    override suspend fun insertUsers(users: List<UserEntity>) = dao.insertAll(users)
}