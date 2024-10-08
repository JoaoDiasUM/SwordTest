package com.example.swordcattest.feature.presentation.catlist

import com.example.swordcattest.feature.domain.model.CatItem

data class CatListScreenState(
    val catList: List<CatItem>? = null,
    val favoriteCatList: List<CatItem>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
