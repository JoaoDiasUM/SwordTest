package com.example.swordcattest.feature_cat_listing.presentation.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun CatDetails(labelResId: Int, value: String?) {
    value?.let {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = labelResId) + " $it"
        )
    }
}