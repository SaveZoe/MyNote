package com.example.myapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.data.dao.CategoryDao
import com.example.myapplication.data.entities.Category
import com.example.myapplication.data.roomdb.AppDB
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RoomCategoryCardTest {
    private lateinit var categoryDao: CategoryDao
    private lateinit var db: AppDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDB::class.java
        ).build()
        categoryDao = db.categoryDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun addCard() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        val test = categoryDao.getAllCategoryCard()
        assertEquals("test", test.first().first().categoryName)
    }

    @Test
    fun deleteCard() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        categoryDao.delCategoryCard(1)
        val test = categoryDao.getAllCategoryCard()
        assertEquals(0, test.first().count())
    }

    @Test
    fun getAllCard() = runBlocking {
        val test = categoryDao.getAllCategoryCard()
        assertEquals(0, test.first().count())
    }

    @Test
    fun getErrorAllCard() = runBlocking {
        val test = categoryDao.getAllCategoryCard()
        assertEquals(1, test.first().count())
    }

    @Test
    fun inCorrectDeleteCard() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        categoryDao.delCategoryCard(1)
        val test = categoryDao.getAllCategoryCard()
        assertEquals(1, test.first().count())
    }

    @Test
    fun inCorrectDeleteCard1() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        categoryDao.delCategoryCard(0)
        val test = categoryDao.getAllCategoryCard()
        assertEquals(0, test.first().count())
    }

    @Test
    fun getCardName() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        val test = categoryDao.getCategoryCard(1)
        assertEquals("test", test.first().first().categoryName)
    }

    @Test
    fun getCardId() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        val test = categoryDao.getCategoryCard(1)
        assertEquals(1, test.first().first().id)
    }

    @Test
    fun getCardId1() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        val test = categoryDao.getAllCategoryCard()
        assertEquals(1, test.first().first().id)
    }

    @Test
    fun getErrorCardId() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        val test = categoryDao.getCategoryCard(0)
        assertEquals(0, test.first().first().id)
    }
    @Test
    fun getErrorCardId1() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test"))
        val test = categoryDao.getCategoryCard(1)
        assertEquals(0, test.first().first().id)
    }

    @Test
    fun getErrorCardName() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test1"))
        val test = categoryDao.getCategoryCard(0)
        assertEquals("test", test.first().first().categoryName)
    }

    @Test
    fun add2Card() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test1"))
        categoryDao.addCategoryCard(Category(categoryName = "test2"))
        val test = categoryDao.getAllCategoryCard()
        assertEquals(2, test.first().count())
    }
    @Test
    fun errorAdd2Card() = runBlocking {
        categoryDao.addCategoryCard(Category(categoryName = "test1"))
        categoryDao.addCategoryCard(Category(categoryName = "test2"))
        val test = categoryDao.getAllCategoryCard()
        assertEquals(1, test.first().count())
    }
}