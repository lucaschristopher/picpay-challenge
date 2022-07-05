package com.picpay.desafio.android.data.datasource.local

import com.picpay.desafio.android.data.util.DataMapper
import com.picpay.desafio.android.data.datasource.local.dao.UserDao
import com.picpay.desafio.android.domain.model.User

class PicPayLocalDataSourceImpl(
    private val dao: UserDao
) : PicPayLocalDataSource {

    override fun getUsers(): List<User> {
        return DataMapper().mapList(this.dao.getUsers())
    }

    override fun saveUser(user: User) {
        this.dao.addUser(DataMapper().mapEntity(user))
    }
}