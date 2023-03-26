package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.data.roomdb.AppDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = AppDB::class.java,
            "AppDB",
        ).build()
    }

    single {
        get<AppDB>().categoryDao()
    }
}
