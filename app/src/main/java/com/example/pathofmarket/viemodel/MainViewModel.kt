package com.example.pathofmarket.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathofmarket.repository.ExchangeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StartScreenUiState(emptyList(), null))
    val uiState: StateFlow<StartScreenUiState> = _uiState.asStateFlow()
    val repo = ExchangeRepository()

    init {
        fetchCurrencyItems()
    }

    private fun fetchCurrencyItems() {
        viewModelScope.launch {
            try {
                val response = repo.getExchangeItems("Runes of Aldur", "Currency")

                _uiState.value = StartScreenUiState(response, null)
            } catch (e: Exception) {
                _uiState.value = StartScreenUiState(emptyList(), e)
            }
        }
    }
}

data class StartScreenUiState(
    val items: List<ExchangeItem>,
    val error: Exception?
)

data class RatesInfo(
    val divineRate: Double,
    val divineImage: String?,
    val exaltedImage: String?
)

data class ExchangeItem(
    val id: String,
    val name: String,
    val image: String,
    val primaryValue: Double,
    val volumePrimaryValue: Double,
    val maxVolumeCurrency: String,
    val maxVolumeRate: Double,
    val divinePrice: Double?,
    val exaltedPrice: Double?,
)