package com.picpay.desafio.android.util

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.User
import io.github.serpro69.kfaker.faker

object Utils {

    private val faker = faker {}

    fun generateUser(
        id: Int = faker.random.nextInt(1, 10),
        name: String = faker.random.nextString(),
        username: String = faker.random.nextString(),
        img: String = faker.random.nextString()
    ): User =
        User(id, name, username, img)

    fun generateUserResponse(
        id: Int = faker.random.nextInt(1, 10),
        name: String = faker.random.nextString(),
        username: String = faker.random.nextString(),
        img: String = faker.random.nextString()
    ) = UserResponse(
        id = id,
        name = name,
        username = username,
        image = img
    )

    fun generateListUser(
        size: Int = faker.random.nextInt(1, 10)
    ): List<User> {
        val users = mutableListOf<User>()

        repeat(size) {
            users.add(generateUser())
        }

        return users.toList()
    }

    fun generateListUserResponse(
        size: Int = faker.random.nextInt(1, 10)
    ): List<UserResponse> {
        val users = mutableListOf<UserResponse>()

        repeat(size) {
            users.add(generateUserResponse())
        }

        return users.toList()
    }
}