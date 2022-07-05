package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.repository.PicPayRepository
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetPicPayUsers(
    private val repository: PicPayRepository
) : GetPicPayUsersUserCase {

    override suspend fun invoke(): Flow<List<User>?> {
        return this.repository.getUsers()
    }
}

interface GetPicPayUsersUserCase {
    suspend operator fun invoke(): Flow<List<User>?>
}