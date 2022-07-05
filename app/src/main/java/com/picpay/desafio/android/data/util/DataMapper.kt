package com.picpay.desafio.android.data.util

import com.picpay.desafio.android.core.mapper.Mapper
import com.picpay.desafio.android.data.datasource.local.model.UserEntity
import com.picpay.desafio.android.domain.model.User

internal class DataMapper : Mapper<List<UserEntity>, List<User>> {
    override fun mapList(input: List<UserEntity>): List<User> {
        return input.map {
            User(it.id, it.img, it.name, it.username)
        }
    }

    fun mapEntity(input: User): UserEntity {
        return UserEntity(input.id, input.img, input.name, input.username)
    }
}