package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.core.util.UseCase
import com.picpay.desafio.android.data.repository.PicPayRepository
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetPicPayUsersUseCase(
    private val repository: PicPayRepository
) : UseCase.NoParam<List<User>>() {

    override suspend fun execute(): Flow<List<User>> {
        return this.repository.getUsers()
    }
}
