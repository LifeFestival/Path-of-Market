package com.example.pathofmarket.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathofmarket.repository.ExchangeRepository
import com.example.pathofmarket.screens.state.RatesDataState
import com.example.pathofmarket.screens.state.RatesErrorState
import com.example.pathofmarket.screens.state.RatesLoadingState
import com.example.pathofmarket.screens.state.RatesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RatesViewModel : ViewModel() {
    private val _ratesUiState = MutableStateFlow<RatesUiState>(RatesLoadingState())
    val ratesUiState: StateFlow<RatesUiState> = _ratesUiState.asStateFlow()
    val repo = ExchangeRepository()

    fun fetchCurrencyItems(catName: String) {
        viewModelScope.launch {
            try {
                val response = repo.getExchangeItems("Runes of Aldur", catName)

                _ratesUiState.value = RatesDataState(response)
            } catch (e: Exception) {
                _ratesUiState.value = RatesErrorState(e)
            }
        }
    }
}