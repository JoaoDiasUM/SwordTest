package com.example.swordcattest.feature.presentation.catlist

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun CatListScreen(
    navController: NavController,
    viewModel: CatViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
}