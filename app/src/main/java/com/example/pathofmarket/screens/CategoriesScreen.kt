package com.example.pathofmarket.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pathofmarket.model.CategoriesDataSet
import com.example.pathofmarket.model.CategoryItem
import com.example.pathofmarket.navigation.Routes
import com.example.pathofmarket.widgets.CategoryItemWidget


//TODO create colour scheme
val colorPalette = listOf(
    Color(0xFFFFB199), // MainLight
    Color(0xFFE67C5C), // Middle
    Color(0xFF801D00)  //MainDark
)

@Composable
fun CategoriesScreen(navController: NavController) {

    val cats: List<CategoryItem> = CategoriesDataSet.categories

    Box(
        modifier = Modifier.background(
            brush = Brush.linearGradient(
                colors = colorPalette,
                start = Offset(0f, 0f),
                end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
            )
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 46.dp)
        ) {
            items(cats) { item ->
                CategoryItemWidget(
                    item,
                    onClick = {
                        navController.navigate(Routes.RatesScreen.createRoute(item.name))
                    })
            }
        }
    }
}