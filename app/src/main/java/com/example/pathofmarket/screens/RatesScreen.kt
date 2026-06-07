package com.example.pathofmarket.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pathofmarket.screens.state.ExchangeItem
import com.example.pathofmarket.screens.state.RatesDataState
import com.example.pathofmarket.screens.state.RatesErrorState
import com.example.pathofmarket.screens.state.RatesLoadingState
import com.example.pathofmarket.viemodel.RatesViewModel
import com.example.pathofmarket.widgets.ExchangeItemWidget

@Composable
fun RatesScreen(
    viewModel: RatesViewModel
) {
    val uiState by viewModel.ratesUiState.collectAsStateWithLifecycle()

    when (uiState) {
        is RatesLoadingState -> RatesLoadingUiState()
        is RatesDataState -> RatesUiContent((uiState as RatesDataState).data)
        is RatesErrorState -> RatesErrorUiState((uiState as RatesErrorState).exception)
    }
}

@Composable
fun RatesUiContent(data: List<ExchangeItem>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = colorPalette,
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        LazyColumn {
            items(data) { item ->
                ExchangeItemWidget(item)
            }
        }
    }
}

@Composable
private fun RatesErrorUiState(e: Exception) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.size(60.dp)) {
                Icon(
                    Icons.Filled.Warning,
                    "",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                e.message ?: "-",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
private fun RatesLoadingUiState() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}