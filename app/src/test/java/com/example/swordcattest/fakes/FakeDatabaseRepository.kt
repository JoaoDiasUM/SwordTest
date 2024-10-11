package com.example.swordcattest.fakes

import com.example.swordcattest.feature_cat_listing.domain.model.Breed
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.repository.DatabaseRepository

class FakeDatabaseRepository : DatabaseRepository {
    private val breed = Breed("1", "1", "1", "1", "1", "1")
    private val cat = CatItem("1", listOf(breed), 1, "example", 1, false)

    private val catList = mutableListOf(cat)
    override suspend fun getAllCats(): List<CatItem> {
        return catList
    }

    override suspend fun insertAllCats(cats: List<CatItem>) {
        catList.addAll(cats)
    }

    override suspend fun insertCats(cat: CatItem) {
        catList.add(cat)
    }
}