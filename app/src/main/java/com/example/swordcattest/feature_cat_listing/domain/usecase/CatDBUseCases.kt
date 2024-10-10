package com.example.swordcattest.feature_cat_listing.domain.usecase

data class CatDBUseCases(
    val getAllCats: GetAllCats,
    val insertAllCats: InsertAllCats,
    val insertCat: InsertCat,
)