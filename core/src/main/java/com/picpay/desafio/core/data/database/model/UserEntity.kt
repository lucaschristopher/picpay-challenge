package com.picpay.desafio.core.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

private const val TABLE = "table_users"

@Entity(tableName = TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
)