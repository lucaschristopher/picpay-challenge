package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.core.presentation.model.State
import com.picpay.desafio.core.presentation.utils.toUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal fun interface GetUsersUseCase {
    fun invoke(isConnected: Boolean): Flow<State<List<UserModel>>>
}

internal class GetUsersUseCaseImpl(
    private val repository: PicPayRepository
) : GetUsersUseCase {
    override operator fun invoke(isConnected: Boolean): Flow<State<List<UserModel>>> = flow {
        val result = repository.getUsers(isConnected)
        emit(result.toUiState())
    }
}

