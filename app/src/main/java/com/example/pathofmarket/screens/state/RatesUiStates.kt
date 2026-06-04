package com.example.pathofmarket.screens.state

sealed class RatesUiState

class LoadingState() : RatesUiState()
class ErrorState(val exception: Exception) : RatesUiState()
class RatesDataState(val data: List<ExchangeItem>) : RatesUiState()

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
    val totalChange: Int,
    val sparkline: List<Double>
)