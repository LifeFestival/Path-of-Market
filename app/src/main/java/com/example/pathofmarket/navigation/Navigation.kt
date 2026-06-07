package com.example.pathofmarket.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pathofmarket.screens.CategoriesScreen
import com.example.pathofmarket.screens.RatesScreen
import com.example.pathofmarket.screens.StartScreen
import com.example.pathofmarket.viemodel.RatesViewModel
import com.example.pathofmarket.viemodel.StartViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val ratesViewModel: RatesViewModel = viewModel()
    val startViewModel: StartViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.StartScreen.name) {
        composable(route = Routes.StartScreen.name) {
            StartScreen(startViewModel, navController)
        }
        composable(route = Routes.CategoriesScreen.name) {
            CategoriesScreen(navController)
        }
        composable(route = Routes.RatesScreen.name) {
            RatesScreen(ratesViewModel)
        }
    }
}

enum class Routes {
    StartScreen,
    CategoriesScreen,
    RatesScreen
}