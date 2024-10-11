package com.example.swordcattest.feature_cat_listing.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.swordcattest.R
import com.example.swordcattest.feature_cat_listing.presentation.screens.components.CatList
import com.example.swordcattest.feature_cat_listing.presentation.ui.components.LoadingIndicator
import com.example.swordcattest.feature_cat_listing.presentation.ui.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListFavoritesScreen(
    navController: NavController,
    viewModel: CatViewModel
) {

    val searchText = viewModel.searchText.collectAsStateWithLifecycle()

    val cats = viewModel.cats.collectAsStateWithLifecycle()

    val isSearching = viewModel.isSearching.collectAsStateWithLifecycle()

    // Improve to use common base UI components

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchBar(searchText = searchText.value, viewModel = viewModel)

            Spacer(modifier = Modifier.height(16.dp))

            if (isSearching.value) {
                LoadingIndicator()
            } else {
                CatList(
                    cats = cats.value.filter { it.favourite },
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}