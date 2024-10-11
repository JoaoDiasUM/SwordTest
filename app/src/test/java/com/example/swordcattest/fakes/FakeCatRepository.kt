package com.example.swordcattest.fakes

import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.repository.CatRepository

class FakeCatRepository: CatRepository {
    override suspend fun getCats(): List<CatItem>? {
        return listOf(FakeCat.cat,FakeCat.cat)
    }
}