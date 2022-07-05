package com.picpay.desafio.android.core.mapper

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.User

internal class DomainMapper : Mapper<List<UserResponse>, List<User>> {
    override fun mapList(input: List<UserResponse>): List<User> {
        return input.map {
            User(it.id, it.image, it.name, it.username)
        }
    }
}