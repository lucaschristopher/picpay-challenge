package com.picpay.desafio.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.core.data.database.dao.UserDao
import com.picpay.desafio.core.data.database.model.UserEntity

internal const val DATABASE_NAME = "picpay_database"

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
internal abstract class PicPayDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PicPayDatabase? = null

        fun getDatabase(context: Context): PicPayDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PicPayDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}