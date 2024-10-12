package com.example.swordcattest.feature_cat_listing.presentation.screens.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.swordcattest.R

@Composable
fun CatAlertDialog(shouldShowDialog: Boolean, errorMessage: String) {

    var showDialog by remember { mutableStateOf(shouldShowDialog) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = { Text(text = stringResource(id = R.string.error)) },
            text = { Text(text = errorMessage) },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.error_close),
                        color = Color.White
                    )
                }
            }
        )
    }
}