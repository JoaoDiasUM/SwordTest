package com.example.swordcattest.feature_cat_listing.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.presentation.screens.CatViewModel

@Composable
fun CatList(cats: List<CatItem>, navController: NavController, viewModel: CatViewModel) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true
    ) {
        items(
            items = cats,
            key = { cat -> cat.id }
        ) { cat ->
            CatListItem(
                cat = cat,
                false,
                onFavoritesClick = { viewModel.onFavoritesClick(cat) },
                onImageClick = { navController.navigate("cat_details_list_screen/${cat.id}") }
            )
        }
    }
}