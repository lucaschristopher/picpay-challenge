package com.picpay.desafio.android.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val img: String,
    val name: String,
    val username: String
)