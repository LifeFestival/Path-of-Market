package com.example.pathofmarket.screens.state

sealed class StartUiState

class StartLoadingState : StartUiState()
class StartErrorState(val exception: Exception) : StartUiState()
class StartSuccessState : StartUiState()