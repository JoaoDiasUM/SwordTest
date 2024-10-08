package com.example.swordcattest.feature.presentation.ui

import androidx.compose.material.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}