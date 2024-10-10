package com.example.swordcattest.feature.presentation.catlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.swordcattest.R
import com.example.swordcattest.feature.presentation.Screen
import com.example.swordcattest.feature.presentation.ui.components.CatListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    navController: NavController,
    viewModel: CatViewModel
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    val searchText = viewModel.searchText.collectAsStateWithLifecycle()

    val cats = viewModel.cats.collectAsStateWithLifecycle()

    val isSearching = viewModel.isSearching.collectAsStateWithLifecycle()

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
            TextField(
                value = searchText.value,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text(text = "Search") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isSearching.value) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = true
                ) {
                    items(
                        items = cats.value,
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
        }
    }
}