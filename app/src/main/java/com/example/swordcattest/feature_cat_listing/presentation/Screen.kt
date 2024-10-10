package com.example.swordcattest.feature_cat_listing.presentation

sealed class Screen(val route: String) {
    data object CatListScreen : Screen("cat_list_screen")

    data object CatFavouritesListScreen : Screen("cat_favourites_list_screen")

    data object CatDetailsScreen : Screen("cat_details_list_screen/{catId}")
}