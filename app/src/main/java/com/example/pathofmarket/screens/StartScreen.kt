package com.example.pathofmarket.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pathofmarket.viemodel.MainViewModel
import com.example.pathofmarket.viemodel.StartScreenUiState
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import com.example.pathofmarket.widgets.ExchangeItemWidget

@Composable
fun StartScreen(
    viewModel: MainViewModel = MainViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val error = uiState.error

    if (error == null) {
        StartScreenContent(
            uiState
        )
    } else {
        StartScreenErrorState(error)
    }
}

@Composable
fun StartScreenContent(uiState: StartScreenUiState) {
    Box(
        modifier = Modifier.fillMaxSize().padding(top = 32.dp)
    ) {
        LazyColumn {
            items(uiState.items) { item ->
                ExchangeItemWidget(item)
            }
        }
    }
}

@Composable
fun StartScreenErrorState(e: Exception) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Icon(Icons.Filled.Warning, "", tint = Color.White)
            Text(e.message ?: "-", color = Color.White)
        }
    }
}