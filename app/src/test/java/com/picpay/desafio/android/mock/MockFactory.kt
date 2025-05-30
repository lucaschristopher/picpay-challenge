package com.picpay.desafio.android.mock

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.core.data.database.model.UserEntity

// Response

val userResponse1 = UserResponse(
    id = 1,
    name = "Sandrine Spinka",
    username = "Tod86",
    image = "https://randomuser.me/api/portraits/men/1.jpg",
)

val userResponse2 = UserResponse(
    id = 2,
    name = "Carli Carroll",
    username = "Constantin_Sawayn",
    image = "https://randomuser.me/api/portraits/men/2.jpg"
)

val userResponse3 = UserResponse(
    id = 3,
    name = "Annabelle Reilly",
    username = "Lawrence_Nader62",
    image = "https://randomuser.me/api/portraits/men/3.jpg"
)

val userResponseList = listOf(userResponse1, userResponse2, userResponse3)

// Entity

val userEntity1 = UserEntity(
    id = 1,
    name = "Sandrine Spinka",
    username = "Tod86",
    image = "https://randomuser.me/api/portraits/men/1.jpg",
)

val userEntity2 = UserEntity(
    id = 2,
    name = "Carli Carroll",
    username = "Constantin_Sawayn",
    image = "https://randomuser.me/api/portraits/men/2.jpg"
)

val userEntity3 = UserEntity(
    id = 3,
    name = "Annabelle Reilly",
    username = "Lawrence_Nader62",
    image = "https://randomuser.me/api/portraits/men/3.jpg"
)

val userEntityList = listOf(userEntity1, userEntity2, userEntity3)

// Model

val userModel1 = UserModel(
    id = "1",
    name = "Sandrine Spinka",
    username = "Tod86",
    image = "https://randomuser.me/api/portraits/men/1.jpg",
)

val userModel2 = UserModel(
    id = "2",
    name = "Carli Carroll",
    username = "Constantin_Sawayn",
    image = "https://randomuser.me/api/portraits/men/2.jpg"
)

val userModel3 = UserModel(
    id = "3",
    name = "Annabelle Reilly",
    username = "Lawrence_Nader62",
    image = "https://randomuser.me/api/portraits/men/3.jpg"
)

val userModelList = listOf(userModel1, userModel2, userModel3)