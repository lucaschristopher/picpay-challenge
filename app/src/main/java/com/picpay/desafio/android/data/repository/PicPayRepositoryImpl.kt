package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.core.exception.RemoteException
import com.picpay.desafio.android.core.util.Constants
import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.util.CacheUtils
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.util.DomainMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class PicPayRepositoryImpl(
    private val remoteDataSource: PicPayRemoteDataSource,
    private val localDataSource: PicPayLocalDataSource,
    private val dispatcher: CoroutineDispatcher
) : PicPayRepository {

    override suspend fun getUsers(): Flow<List<User>> = flow {
        try {
            if (CacheUtils.shouldGetDataInCache() && checkUsersInDataBase()) {
                emit(getUsersInLocalDataBase())
            } else {
                remoteDataSource.getUsers().collect { result ->
                    val users = DomainMapper().mapList(result)
                    users.map { addUserInLocalDataBase(it) }
                    emit(users)
                }
            }
        } catch (exception: HttpException) {
            throw RemoteException(exception.message ?: Constants.ERROR_HTTP_MESSAGE)
        }
    }.flowOn(dispatcher)

    private fun addUserInLocalDataBase(user: User) {
        this.localDataSource.saveUser(user)
    }

    private fun checkUsersInDataBase() = getUsersInLocalDataBase().isNotEmpty()

    private fun getUsersInLocalDataBase() = this.localDataSource.getUsers()

}
