package com.example.swordcattest.fakes

import com.example.swordcattest.feature_cat_listing.domain.model.Breed
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem

object FakeCat {
    private val breed = Breed("1", "1", "1", "1", "1", "1")
    val cat = CatItem("1", listOf(breed), 1, "example", 1, false)
}