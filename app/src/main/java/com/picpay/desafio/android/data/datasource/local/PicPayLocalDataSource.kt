package com.picpay.desafio.android.data.datasource.local

import com.picpay.desafio.android.domain.model.User

interface PicPayLocalDataSource {

    fun getUsers(): List<User>

    fun saveUser(user: User)
}