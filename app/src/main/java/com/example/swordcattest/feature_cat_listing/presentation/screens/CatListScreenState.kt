package com.example.swordcattest.feature_cat_listing.presentation.screens

data class CatListScreenState(
    val error: String = "",
    val isLoading: Boolean? = false,
    val shouldShowErrorDialog: Boolean = false,
)
