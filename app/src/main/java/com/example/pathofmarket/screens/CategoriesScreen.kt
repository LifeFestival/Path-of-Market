package com.example.pathofmarket.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pathofmarket.model.CategoriesDataSet
import com.example.pathofmarket.model.CategoryItem
import com.example.pathofmarket.navigation.Routes
import com.example.pathofmarket.theme.PathOfMarketColors
import com.example.pathofmarket.widgets.CategoryItemWidget

@Composable
fun CategoriesScreen(navController: NavController) {

    val cats: List<CategoryItem> = CategoriesDataSet.categories

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            PathOfMarketColors.PrimaryVariant.copy(alpha = 0.15f),
                            Color.Transparent
                        ),
                        radius = 800f
                    )
                )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 46.dp)
        ) {
            item {
                Text(
                    text = "General",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                HorizontalDivider(
                    color = PathOfMarketColors.Primary.copy(alpha = 0.4f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
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