package com.example.pathofmarket.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pathofmarket.screens.state.ErrorState
import com.example.pathofmarket.screens.state.ExchangeItem
import com.example.pathofmarket.screens.state.LoadingState
import com.example.pathofmarket.screens.state.RatesDataState
import com.example.pathofmarket.viemodel.MainViewModel
import com.example.pathofmarket.widgets.ExchangeItemWidget

@Composable
fun RatesScreen(
    viewModel: MainViewModel = MainViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is LoadingState -> RatesLoadingState()
        is RatesDataState -> RatesScreenContent((uiState as RatesDataState).data)
        is ErrorState -> RatesScreenErrorState((uiState as ErrorState).exception)
    }
}

@Composable
fun RatesScreenContent(data: List<ExchangeItem>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        LazyColumn {
            items(data) { item ->
                ExchangeItemWidget(item)
            }
        }
    }
}

@Composable
fun RatesScreenErrorState(e: Exception) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Icon(Icons.Filled.Warning, "", tint = Color.White)
            Text(e.message ?: "-", color = Color.White)
        }
    }
}

@Composable
fun RatesLoadingState() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}