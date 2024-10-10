package com.example.swordcattest.feature_cat_listing.domain.usecase

import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.repository.DatabaseRepository
import javax.inject.Inject

class InsertCat @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(cats: CatItem) =
        databaseRepository.insertCats(cats)
}