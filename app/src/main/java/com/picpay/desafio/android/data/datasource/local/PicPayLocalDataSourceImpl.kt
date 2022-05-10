package com.picpay.desafio.android.data.datasource.local

import com.picpay.desafio.android.data.datasource.local.model.UserEntity

class PicPayLocalDataSourceImpl(
    private val userDao: UserDao
) : PicPayLocalDataSource {

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getUsers()
    }
}