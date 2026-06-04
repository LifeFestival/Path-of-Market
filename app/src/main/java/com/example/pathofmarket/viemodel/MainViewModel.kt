package com.example.pathofmarket.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathofmarket.repository.ExchangeRepository
import com.example.pathofmarket.screens.state.ErrorState
import com.example.pathofmarket.screens.state.LoadingState
import com.example.pathofmarket.screens.state.RatesDataState
import com.example.pathofmarket.screens.state.RatesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<RatesUiState>(LoadingState())
    val uiState: StateFlow<RatesUiState> = _uiState.asStateFlow()
    val repo = ExchangeRepository()

    init {
        fetchCurrencyItems()
    }

    private fun fetchCurrencyItems() {
        viewModelScope.launch {
            try {
                val response = repo.getExchangeItems("Runes of Aldur", "Currency")

                _uiState.value = RatesDataState(response)
            } catch (e: Exception) {
                _uiState.value = ErrorState(e)
            }
        }
    }
}