package com.example.cryptoview.core.repositories

import com.example.cryptoview.core.remote.DefaultRemoteDataSource
import com.example.cryptoview.datasource.network.mapper.MarketChartMapper
import javax.inject.Inject

class MarketChartRepository @Inject constructor(
    private val networkDataSource: DefaultRemoteDataSource,
) {

    suspend fun fetchMarketChartData(
        coinId: String,
        currency: String,
        days: Int
    ): List<List<Number>> {
        return networkDataSource.fetchMarketChartData(coinId, currency, days)
    }

}