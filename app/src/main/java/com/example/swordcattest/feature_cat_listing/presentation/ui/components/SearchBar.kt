package com.example.swordcattest.feature_cat_listing.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.swordcattest.R
import com.example.swordcattest.feature_cat_listing.presentation.screens.CatViewModel

@Composable
fun SearchBar(searchText: String, viewModel: CatViewModel) {
    TextField(
        value = searchText,
        onValueChange = viewModel::onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text(text = stringResource(id = R.string.search)) },
    )
}