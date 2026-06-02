package com.example.pathofmarket.network.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EconomyService {
    private const val BASE_URL = "https://poe.ninja/poe2/api/economy/exchange/current/"

    val api: EconomyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EconomyApiService::class.java)
    }
}