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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pathofmarket.navigation.Routes
import com.example.pathofmarket.screens.state.StartErrorState
import com.example.pathofmarket.screens.state.StartLoadingState
import com.example.pathofmarket.viemodel.StartViewModel

@Composable
fun StartScreen(viewModel: StartViewModel, navController: NavController) {

    val uiState by viewModel.startUiState.collectAsStateWithLifecycle()

    //TODO create colour scheme
    val colorPalette = listOf(
        Color(0xFFFFB199), // MainLight
        Color(0xFFE67C5C), // Middle
        Color(0xFF801D00)  //MainDark
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = colorPalette,
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Text(
            "Path of\nMarket",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 48.dp),
            color = Color.Black,
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 56.sp
        )
        when (uiState) {
            is StartLoadingState -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black
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
        if (e != null) Text(e.message ?: "Unknown Error, Try Again", color = Color.Black)
        ElevatedButton(
            onClick = {
                navController?.navigate(Routes.RatesScreen.name)
            },
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(60.dp),
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF801D00), // Background color
                contentColor = Color.White,          // Text and icon color
            )
        ) {
            Text(if (e != null) "Retry" else "Start", color = Color(0xFFFFD8CC), fontSize = 16.sp)
        }
    }
}