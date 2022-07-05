package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.util.CacheUtils
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PicPayRepositoryImpl(
    private val remoteDataSource: PicPayRemoteDataSource,
    private val localDataSource: PicPayLocalDataSource,
    private val dispatcher: CoroutineDispatcher
) : PicPayRepository {

    override suspend fun getUsers(): Flow<List<User>?> = flow {
        try {
            if (CacheUtils.shouldGetDataInCache() && checkUsersInDataBase()) {
                emit(getUsersInLocalDataBase())
            } else {
                remoteDataSource.getUsers().collect { users ->
                    users?.map { addUserInLocalDataBase(it) }
                    emit(users)
                }
            }
        } catch (exception: Exception) {
            emit(mutableListOf())
        }
    }.flowOn(dispatcher)

    private fun addUserInLocalDataBase(user: User) {
        this.localDataSource.saveUser(user)
    }

    private fun checkUsersInDataBase() = getUsersInLocalDataBase().isNotEmpty()

    private fun getUsersInLocalDataBase() = this.localDataSource.getUsers()

}
