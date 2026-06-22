package com.example.pathofmarket.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pathofmarket.screens.state.ExchangeItem
import com.example.pathofmarket.screens.state.RatesDataState
import com.example.pathofmarket.screens.state.RatesErrorState
import com.example.pathofmarket.screens.state.RatesLoadingState
import com.example.pathofmarket.theme.PathOfMarketColors
import com.example.pathofmarket.viemodel.RatesViewModel
import com.example.pathofmarket.widgets.ExchangeItemWidget

@Composable
fun RatesScreen(
    viewModel: RatesViewModel,
    catName: String
) {
    val uiState by viewModel.ratesUiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    LaunchedEffect(catName) {
        viewModel.fetchCurrencyItems(catName)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = catName,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(start = 16.dp, top = 48.dp, bottom = 4.dp)
        )
        HorizontalDivider(
            color = PathOfMarketColors.Primary.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        if (uiState is RatesDataState) {
            SearchBarWidget(searchQuery, viewModel::onSearchQueryChanged)
        }

        when (uiState) {
            is RatesLoadingState -> RatesLoadingUiState()
            is RatesDataState -> RatesUiContent((uiState as RatesDataState).data)
            is RatesErrorState -> RatesErrorUiState((uiState as RatesErrorState).exception)
        }
    }
}

@Composable
fun RatesUiContent(data: List<ExchangeItem>) {

    if (data.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            LazyColumn {
                items(data) { item ->
                    ExchangeItemWidget(item)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = PathOfMarketColors.OnSurfaceVariant,
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = "No items found",
                style = MaterialTheme.typography.titleMedium,
                color = PathOfMarketColors.OnSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun RatesErrorUiState(e: Exception) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.size(60.dp)) {
                Icon(
                    Icons.Filled.Warning,
                    null,
                    tint = PathOfMarketColors.Error,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                e.message ?: "Unknown Error!",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
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
        CircularProgressIndicator(
            color = PathOfMarketColors.Primary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun SearchBarWidget(query: String, onQueryChanged: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = {
            Text(
                text = "Search items...",
                color = PathOfMarketColors.OnSurfaceVariant
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = PathOfMarketColors.Primary
            )
        },
        trailingIcon = {
            if (query.isNotBlank()) {
                IconButton(onClick = { onQueryChanged("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear search",
                        tint = PathOfMarketColors.OnSurfaceVariant
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PathOfMarketColors.Primary,
            unfocusedBorderColor = PathOfMarketColors.PrimaryVariant.copy(alpha = 0.5f),
            cursorColor = PathOfMarketColors.Primary,
            focusedTextColor = PathOfMarketColors.OnBackground,
            unfocusedTextColor = PathOfMarketColors.OnBackground,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}