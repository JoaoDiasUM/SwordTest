package com.example.swordcattest.feature_cat_listing.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.swordcattest.feature_cat_listing.data.room.ListConverters
import com.example.swordcattest.feature_cat_listing.data.room.dao.CatDao
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem

@Database(entities = [CatItem::class], version = 1, exportSchema = false)
@TypeConverters(ListConverters::class)
abstract class CatsDatabase : RoomDatabase() {
    abstract fun catsDao(): CatDao
}