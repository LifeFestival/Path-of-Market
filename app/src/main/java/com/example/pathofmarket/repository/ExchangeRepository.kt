package com.example.pathofmarket.repository

import com.example.pathofmarket.network.data.ExchangeItemsResponse
import com.example.pathofmarket.network.services.EconomyService
import com.example.pathofmarket.viemodel.ExchangeItem
import com.example.pathofmarket.viemodel.RatesInfo
import kotlin.math.floor

class ExchangeRepository {

    companion object {
        private const val EXALTED_KEY = "exalted"
        private const val IMAGE_BASE_URL = "https://web.poecdn.com"
    }

    private val _exchangeService = EconomyService.api
    private var _exaltedRate: Double = 0.0

    suspend fun getExchangeItems(
        league: String,
        type: String
    ): List<ExchangeItem> {
        val response = _exchangeService.getCurrencyExchange(league, type)

        _exaltedRate = response.coreDto.rates[EXALTED_KEY] ?: 0.0

        return exchangeResponseMapping(response)
    }

    private fun exchangeResponseMapping(response: ExchangeItemsResponse): List<ExchangeItem> {
        val result = response.items.mapIndexed { index, item ->

            val line = response.lines[index]

            ExchangeItem(
                item.id,
                item.name,
                IMAGE_BASE_URL + item.image,
                line.primaryValue,
                line.volumePrimaryValue,
                line.maxVolumeCurrency,
                line.maxVolumeRate,
                calculateDivinePrice(line.primaryValue),
                calculateExaltedPrice(line.primaryValue * _exaltedRate)
            )
        }

        return result.sortedBy { it.primaryValue }.reversed()
    }

    private fun calculateDivinePrice(value: Double): Double? {
        val price = floor(value * 100) / 100

        if (value <= 0.1) return null

        return price
    }

    private fun calculateExaltedPrice(value: Double): Double {
        val price =
            if (value > 1.0) floor(value)
            else floor(value * 100) / 100

        return price
    }
}