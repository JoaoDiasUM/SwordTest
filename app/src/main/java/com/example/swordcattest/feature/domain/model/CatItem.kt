package com.example.swordcattest.feature.domain.model

import com.example.swordcattest.feature.domain.model.Breed

data class CatItem(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)