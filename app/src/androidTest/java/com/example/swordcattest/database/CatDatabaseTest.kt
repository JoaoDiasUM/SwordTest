package com.example.swordcattest.database

import androidx.compose.material3.darkColorScheme
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.swordcattest.feature_cat_listing.data.room.dao.CatDao
import com.example.swordcattest.feature_cat_listing.data.room.database.CatsDatabase
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CatDatabaseTest {

    private lateinit var catsDatabase: CatsDatabase

    private lateinit var catDao: CatDao

    @Before
    fun setupDatabase() {
        catsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CatsDatabase::class.java
        ).build()

        catDao = catsDatabase.catsDao()
    }

    @After
    fun closeDatabase() {
        catsDatabase.close()
    }

    @Test
    fun insertCat() = runBlocking {
        val cat = CatItem("1", emptyList(), 1, "", 1, false)
        catDao.insertCat(cat)
        assert(catDao.getAllCats().contains(cat))
    }

    @Test
    fun insertAllCats() = runBlocking {
        val cats = listOf(
            CatItem("1", emptyList(), 1, "", 1, false),
            CatItem("2", emptyList(), 1, "", 1, false)
        )
        catDao.insertAllCats(cats)
        assert(catDao.getAllCats().containsAll(cats))
    }

    @Test
    fun getAllCats() = runBlocking {
        val cats = listOf(
            CatItem("1", emptyList(), 1, "", 1, false),
            CatItem("2", emptyList(), 1, "", 1, false)
        )
        catDao.insertAllCats(cats)
        assert(catDao.getAllCats().containsAll(cats))
    }
}