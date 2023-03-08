package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.entities.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Category::class)
    suspend fun addCategoryCard(category: Category)

    @Query("DELETE FROM Category WHERE id = :id")
    fun delCategoryCard(id: Int)

    @Query("SELECT * FROM Category WHERE id = :id")
    fun getCategoryCard(id: Int): Flow<List<Category>>

    @Query("SELECT * FROM Category")
    fun getAllCategoryCard(): Flow<List<Category>>
}
