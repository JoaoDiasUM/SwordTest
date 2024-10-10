package com.example.swordcattest.feature_cat_listing.domain.repository

import com.example.swordcattest.feature_cat_listing.domain.model.CatItem

interface CatRepository {
    suspend fun getCats(): List<CatItem>?
}