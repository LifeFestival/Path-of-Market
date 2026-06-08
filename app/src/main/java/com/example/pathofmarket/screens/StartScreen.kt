package com.example.pathofmarket.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pathofmarket.navigation.Routes
import com.example.pathofmarket.screens.state.StartErrorState
import com.example.pathofmarket.screens.state.StartLoadingState
import com.example.pathofmarket.theme.PathOfMarketColors
import com.example.pathofmarket.viemodel.StartViewModel

@Composable
fun StartScreen(viewModel: StartViewModel, navController: NavController) {

    val uiState by viewModel.startUiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
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

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 48.dp)
        ) {
            Text(
                text = "Path of",
                color = PathOfMarketColors.OnBackground,
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                lineHeight = 56.sp
            )
            Text(
                text = "Market",
                color = PathOfMarketColors.Primary,
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                lineHeight = 56.sp
            )
            Text(
                text = "Still sane, exile?",
                color = PathOfMarketColors.Primary.copy(alpha = 0.4f),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                lineHeight = 25.sp
            )
        }

        when (uiState) {
            is StartLoadingState -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = PathOfMarketColors.Primary
                )
            }

            is StartErrorState -> {
                StartScreenContent((uiState as StartErrorState).exception, null)
            }

            else -> {
                StartScreenContent(null, navController)
            }
        }
    }
}

@Composable
fun StartScreenContent(e: Exception?, navController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 64.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (e != null) {
            Text(
                text = e.message ?: "Unknown error, try again",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        HorizontalDivider(
            color = PathOfMarketColors.Primary.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ElevatedButton(
            onClick = {
                navController?.navigate(Routes.CategoriesScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(60.dp),
            border = BorderStroke(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        PathOfMarketColors.Primary,
                        PathOfMarketColors.PrimaryVariant
                    )
                )
            ),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = PathOfMarketColors.Primary
            )
        ) {
            Text(
                text = if (e != null) "Retry" else "Enter Market",
                color = PathOfMarketColors.Primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                letterSpacing = 2.sp
            )
        }
    }
}