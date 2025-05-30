package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.core.data.database.model.UserEntity

fun UserEntity.toModel() = UserModel(
    id = this.id.toString(),
    username = this.username,
    name = this.name,
    image = this.image,
)

fun UserResponse.toEntity() = UserEntity(
    id = this.id.toInt(),
    image = this.image,
    name = this.name,
    username = this.username,
)

fun UserResponse.toModel() = UserModel(
    id = this.id.toString(),
    name = this.name,
    username = this.username,
    image = this.image,
)