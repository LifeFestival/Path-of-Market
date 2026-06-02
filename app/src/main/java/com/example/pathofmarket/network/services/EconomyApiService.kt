package com.example.pathofmarket.network.services

import com.example.pathofmarket.network.data.ExchangeItemsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EconomyApiService {
    @GET("overview")
    suspend fun getCurrencyExchange(
        @Query("league") league: String,
        @Query("type") type: String
    ): ExchangeItemsResponse
}