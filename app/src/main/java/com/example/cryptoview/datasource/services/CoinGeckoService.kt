package com.example.cryptoview.datasource.services

import com.example.cryptoview.datasource.network.api.CoinGeckoApi
import com.example.cryptoview.datasource.network.model.CoinObjectResponse
import com.example.cryptoview.datasource.network.model.ExchangeObjectResponse
import com.example.cryptoview.datasource.network.model.MarketChartObjectResponse

interface CoinGeckoService {

    suspend fun getCoinById(id: String): CoinObjectResponse

    suspend fun getAllCoins(currency: String): List<CoinObjectResponse>

    suspend fun getMarketChart(
        coinId: String,
        currency: String,
        days: Int
    ): MarketChartObjectResponse

    suspend fun getAllExchanges(): List<ExchangeObjectResponse>
}

class DefaultCoinGeckoService constructor(
    private val coinGeckoApi: CoinGeckoApi
) : CoinGeckoService {

    override suspend fun getAllCoins(
        currency: String
    ): List<CoinObjectResponse> {
        return coinGeckoApi.getAllCoins(currency)
    }

    override suspend fun getCoinById(id: String): CoinObjectResponse {
        return coinGeckoApi.getCoinById(id)
    }

    override suspend fun getMarketChart(
        coinId: String,
        currency: String,
        days: Int
    ): MarketChartObjectResponse {
        return coinGeckoApi.getMarketChart(coinId, currency, days)
    }

    override suspend fun getAllExchanges(): List<ExchangeObjectResponse> {
        return coinGeckoApi.getAllExchanges()
    }
}
