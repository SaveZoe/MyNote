package com.example.myapplication.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.CategoryDao
import com.example.myapplication.data.entities.Category

@Database(
    entities = [
        Category::class,
    ],
    version = 1
)
abstract class AppDB : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
