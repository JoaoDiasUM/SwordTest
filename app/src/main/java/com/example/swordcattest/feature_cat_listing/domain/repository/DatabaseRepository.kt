package com.example.swordcattest.feature_cat_listing.domain.repository

import com.example.swordcattest.feature_cat_listing.domain.model.CatItem

interface DatabaseRepository {
    suspend fun getAllCats(): List<CatItem>
    suspend fun insertAllCats(cats: List<CatItem>)
    suspend fun insertCats(cat: CatItem)
}