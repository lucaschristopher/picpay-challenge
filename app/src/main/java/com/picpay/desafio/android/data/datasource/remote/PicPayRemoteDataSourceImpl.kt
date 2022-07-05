package com.picpay.desafio.android.data.datasource.remote

import com.picpay.desafio.android.core.util.ApiUtils.Companion.safeApiCall
import com.picpay.desafio.android.core.mapper.DomainMapper
import com.picpay.desafio.android.core.state.RequestState
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PicPayRemoteDataSourceImpl(
    private val service: PicPayService
) : PicPayRemoteDataSource {

    override suspend fun getUsers(): Flow<List<User>?> = flow {
        val response = safeApiCall({ service.getUsers() }, DomainMapper())
        emit(response)
    }
}
