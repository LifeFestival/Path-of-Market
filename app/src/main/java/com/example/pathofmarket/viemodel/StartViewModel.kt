package com.example.pathofmarket.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathofmarket.screens.state.StartLoadingState
import com.example.pathofmarket.screens.state.StartSuccessState
import com.example.pathofmarket.screens.state.StartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartViewModel : ViewModel () {
    private val _startUiState = MutableStateFlow<StartUiState>(StartSuccessState())
    val startUiState: StateFlow<StartUiState> = _startUiState.asStateFlow()

    init {
        getGlobalInfo()
    }

    fun getGlobalInfo() {
        viewModelScope.launch {

        }
    }
}