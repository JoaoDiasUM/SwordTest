package com.example.swordcattest.feature_cat_listing.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem

@Dao
interface CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(cat: CatItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCats(cats: List<CatItem>)
    @Query("SELECT * FROM cats")
    suspend fun getAllCats(): List<CatItem>
}