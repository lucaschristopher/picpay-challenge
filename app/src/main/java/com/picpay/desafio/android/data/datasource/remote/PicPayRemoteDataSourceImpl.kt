package com.picpay.desafio.android.data.datasource.remote

import com.picpay.desafio.android.core.OutputState
import com.picpay.desafio.android.core.exception.GetUsersException
import com.picpay.desafio.android.core.exception.InternalErrorException
import com.picpay.desafio.android.core.parseResponse
import com.picpay.desafio.android.core.toUser
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpURLConnection

class PicPayRemoteDataSourceImpl(private val service: PicPayService) : PicPayRemoteDataSource {
    override suspend fun getUsers() = flow {

        val result = service.getUsers().parseResponse()

        when (result) {
            is OutputState.Success -> {
                val users = result.value
                emit(users.map { it.toUser() })
            }
            is OutputState.Failure -> {
                if (result.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR)
                    throw InternalErrorException()
                else
                    throw GetUsersException()
            }
        }
    }
}

interface PicPayRemoteDataSource {
    suspend fun getUsers(): Flow<List<User>>
}