package com.example.swordcattest.feature_cat_listing.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.swordcattest.R
import com.example.swordcattest.feature_cat_listing.presentation.catlist.CatDetailsScreen
import com.example.swordcattest.feature_cat_listing.presentation.catlist.CatListFavoritesScreen
import com.example.swordcattest.feature_cat_listing.presentation.catlist.CatListScreen
import com.example.swordcattest.feature_cat_listing.presentation.catlist.CatViewModel
import com.example.swordcattest.feature_cat_listing.presentation.ui.components.TabBarItem
import com.example.swordcattest.feature_cat_listing.presentation.ui.components.TabView
import com.example.swordcattest.feature_cat_listing.presentation.ui.theme.SwordProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val catListTab = TabBarItem(
                title = getString(R.string.bottom_bar_cat_title),
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = Screen.CatListScreen.route,
            )
            val catListFavourites = TabBarItem(
                title = getString(R.string.bottom_bar_favorites_title),
                selectedIcon = Icons.Filled.Notifications,
                unselectedIcon = Icons.Outlined.Notifications,
                route = Screen.CatFavouritesListScreen.route,
            )

            val tabBarItems = listOf(catListTab, catListFavourites)

            val navController = rememberNavController()

            val viewModel: CatViewModel = hiltViewModel()

            SwordProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = { TabView(tabBarItems, navController) }) {
                        NavHost(navController = navController, startDestination = Screen.CatListScreen.route) {
                            composable(
                                route = Screen.CatListScreen.route
                            ) {
                                CatListScreen(navController,viewModel)
                            }
                            composable(
                                route = Screen.CatFavouritesListScreen.route
                            ) {
                                CatListFavoritesScreen(navController,viewModel)
                            }
                            composable(
                                route = Screen.CatDetailsScreen.route,
                                arguments = listOf(navArgument("catId") {
                                    defaultValue = ""
                                })
                            ) {backStackEntry ->
                                backStackEntry.arguments?.getString("catId")?.let {
                                    CatDetailsScreen(catId = it, viewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwordProjectTheme {
        Greeting("Android")
    }
}