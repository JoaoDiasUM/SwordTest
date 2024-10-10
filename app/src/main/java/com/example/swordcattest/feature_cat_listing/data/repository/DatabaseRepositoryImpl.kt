package com.example.swordcattest.feature_cat_listing.data.repository

import com.example.swordcattest.feature_cat_listing.data.room.dao.CatDao
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val catDao: CatDao,
): DatabaseRepository {
    override suspend fun getAllCats(): List<CatItem> {
        return catDao.getAllCats()
    }

    override suspend fun insertAllCats(cats: List<CatItem>) {
        return catDao.insertAllCats(cats)
    }

    override suspend fun insertCats(cat: CatItem) {
        return catDao.insertCat(cat)
    }
}