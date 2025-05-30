package com.picpay.desafio.core.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

private const val TABLE = "table_users"

@Entity(tableName = TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "username")
    val username: String,
)