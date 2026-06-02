package com.example.pathofmarket.repository

import com.example.pathofmarket.network.data.ExchangeItemsResponse
import com.example.pathofmarket.network.services.EconomyService
import com.example.pathofmarket.viemodel.ExchangeItem

class ExchangeRepository {

    private val _exchangeService = EconomyService.api

    suspend fun getExchangeItems(
        league: String = "",
        type: String = "currency"
    ): List<ExchangeItem> {
        val response = _exchangeService.getCurrencyExchange(league, type)

        return exchangeResponseMapping(response)
    }

    private fun exchangeResponseMapping(response: ExchangeItemsResponse): List<ExchangeItem> {
        val result = response.items.mapIndexed { index, item ->

            val line = response.lines[index]

            ExchangeItem(
                item.id,
                item.name,
                item.image,
                line.primaryValue,
                line.volumePrimaryValue,
                line.maxVolumeCurrency,
                line.maxVolumeRate
            )
        }

        return result
    }
}