package com.example.cryptoview.datasource.network.api

import com.example.cryptoview.datasource.network.model.CoinObjectResponse
import com.example.cryptoview.datasource.network.model.ExchangeObjectResponse
import com.example.cryptoview.datasource.network.model.MarketChartObjectResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET("coins/{id}")
    suspend fun getCoinById(
        @Path(value = "id", encoded = true) coinId: String
    ): CoinObjectResponse

    @GET("coins/markets")
    suspend fun getAllCoins(
        @Query("vs_currency") currency: String
    ): List<CoinObjectResponse>

    @GET("exchanges")
    suspend fun getAllExchanges(): List<ExchangeObjectResponse>

    @GET("coins/{id}/market_chart")
    suspend fun getMarketChart(
        @Path(value = "id", encoded = true) coinId: String,
        @Query("vs_currency") currency: String,
        @Query("days") days: Int
    ): MarketChartObjectResponse
}
