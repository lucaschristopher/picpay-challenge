package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.mapper.toEntity
import com.picpay.desafio.android.data.mapper.toModel
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.repository.PicPayRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext

internal class PicPayRepositoryImpl(
    private val remoteDataSource: PicPayRemoteDataSource,
    private val localDataSource: PicPayLocalDataSource,
    private val dispatcher: CoroutineDispatcher,
) : PicPayRepository {

    override suspend fun getUsers(isConnected: Boolean): Result<List<UserModel>> =
        withContext(dispatcher) {
            return@withContext (dispatcher) {
                if (isConnected) {
                    remoteDataSource.getUsers().mapCatching { response ->
                        val entities = response.map { it.toEntity() }
                        localDataSource.insertUsers(entities)
                        response.map { it.toModel() }
                    }
                } else {
                    val cached = localDataSource.getUsers()
                    Result.success(cached.map { it.toModel() })
                }
            }
        }
}