package com.picpay.desafio.core.data.database.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.core.data.database.DATABASE_NAME
import com.picpay.desafio.core.data.database.PicPayDatabase
import com.picpay.desafio.core.data.database.dao.UserDao
import org.koin.dsl.module

object DatabaseDI {
    val module = module {
        single<PicPayDatabase> {
            Room.databaseBuilder(get<Context>(), PicPayDatabase::class.java, DATABASE_NAME).build()
        }
        single<UserDao> { get<PicPayDatabase>().userDao }
    }
}