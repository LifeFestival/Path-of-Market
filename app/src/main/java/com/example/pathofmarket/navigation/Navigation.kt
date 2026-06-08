package com.example.pathofmarket.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pathofmarket.screens.CategoriesScreen
import com.example.pathofmarket.screens.RatesScreen
import com.example.pathofmarket.screens.StartScreen
import com.example.pathofmarket.viemodel.RatesViewModel
import com.example.pathofmarket.viemodel.StartViewModel

private const val catNameKey = "catName"
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val ratesViewModel: RatesViewModel = viewModel()
    val startViewModel: StartViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.StartScreen.route) {
        composable(route = Routes.StartScreen.route) {
            StartScreen(startViewModel, navController)
        }
        composable(route = Routes.CategoriesScreen.route) {
            CategoriesScreen(navController)
        }
        composable(
            route = Routes.RatesScreen.route,
            arguments = listOf(
                navArgument(catNameKey) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            RatesScreen(ratesViewModel, catName = backStackEntry.arguments?.getString(catNameKey) ?: "")
        }
    }
}

sealed class Routes(val route: String) {
    object StartScreen : Routes("start")
    object CategoriesScreen : Routes("categories")
    object RatesScreen : Routes("rates/{catName}") {
        fun createRoute(catName: String) = "rates/$catName"
    }
}