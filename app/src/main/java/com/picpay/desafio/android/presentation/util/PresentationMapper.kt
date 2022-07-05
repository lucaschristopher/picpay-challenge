package com.picpay.desafio.android.presentation.util

import com.picpay.desafio.android.core.mapper.Mapper
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.model.UserState

internal class PresentationMapper : Mapper<List<User>, List<UserState>> {
    override fun mapList(input: List<User>): List<UserState> {
        return input.map {
            UserState(it.img, it.name, it.username)
        }
    }
}
