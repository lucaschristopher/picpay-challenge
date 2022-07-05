package com.picpay.desafio.android.data.datasource.local.dao

import androidx.room.*
import com.picpay.desafio.android.data.datasource.local.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM table_users")
    fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userEntity: UserEntity)
}