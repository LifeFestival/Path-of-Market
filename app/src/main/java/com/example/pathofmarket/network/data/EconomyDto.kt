package com.example.pathofmarket.network.data

import com.google.gson.annotations.SerializedName

data class ExchangeItemsResponse(
    @SerializedName("core") val coreDto: CoreDto,
    @SerializedName("lines") val lines: List<LineDto>,
    @SerializedName("items") val items: List<CurrencyItemDto>
)

data class CoreDto(
    @SerializedName("items") val items: List<CurrencyItemDto>,
    @SerializedName("rates") val rates: Map<String, Double>,
    @SerializedName("primary") val primary: String,
    @SerializedName("secondary") val secondary: String
)

data class CurrencyItemDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)

data class RatesDto(
    @SerializedName("divine") val divine: String,
    @SerializedName("chaos") val chaos: String,
)

data class LineDto(
    @SerializedName("id") val id: String,
    @SerializedName("primaryValue") val primaryValue: Double,
    @SerializedName("volumePrimaryValue") val volumePrimaryValue: Double,
    @SerializedName("maxVolumeCurrency") val maxVolumeCurrency: String,
    @SerializedName("maxVolumeRate") val maxVolumeRate: Double
)
