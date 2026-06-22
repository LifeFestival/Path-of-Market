package com.example.pathofmarket.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathofmarket.repository.ExchangeRepository
import com.example.pathofmarket.screens.state.ExchangeItem
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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var fullList: List<ExchangeItem> = emptyList()

    val repo = ExchangeRepository()

    fun fetchCurrencyItems(catName: String) {
        _ratesUiState.value = RatesLoadingState()
        _searchQuery.value = ""

        viewModelScope.launch {
            try {
                val response = repo.getExchangeItems("Runes of Aldur", catName)

                fullList = response

                _ratesUiState.value = RatesDataState(response)
            } catch (e: Exception) {
                _ratesUiState.value = RatesErrorState(e)
            }
        }
    }

    fun onSearchQueryChanged(query: String) {

        _searchQuery.value = query

        if (query.isBlank()) {
            _ratesUiState.value = RatesDataState(fullList)
        } else {
            _ratesUiState.value =
                RatesDataState(fullList.filter { it.name.contains(query, ignoreCase = true) })
        }
    }
}