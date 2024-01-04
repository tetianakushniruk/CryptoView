package com.example.cryptoview.core.remote

import com.example.cryptoview.core.domain.CoinDomainModel
import com.example.cryptoview.core.domain.ExchangeDomainModel
import com.example.cryptoview.datasource.services.CoinGeckoService
import com.example.cryptoview.datasource.network.mapper.CoinMapper
import com.example.cryptoview.datasource.network.mapper.ExchangeMapper
import com.example.cryptoview.datasource.network.mapper.MarketChartMapper
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun fetchExchanges(): ExchangeDomainModel

    suspend fun fetchCoins(currency: String): CoinDomainModel

    suspend fun fetchMarketChartData(
        coinId: String,
        currency: String,
        days: Int
    ): List<List<Number>>
}

class DefaultRemoteDataSource @Inject constructor(
    private val apiService: CoinGeckoService,
    private val coinMapper: CoinMapper,
    private val exchangeMapper: ExchangeMapper,
    private val marketChartMapper: MarketChartMapper

) : RemoteDataSource {

    override suspend fun fetchCoins(currency: String): CoinDomainModel {
        return coinMapper.mapFromList(apiService.getAllCoins(currency))
    }

    override suspend fun fetchExchanges(): ExchangeDomainModel {
        return exchangeMapper.mapFromList(apiService.getAllExchanges())
    }

    override suspend fun fetchMarketChartData(
        coinId: String,
        currency: String,
        days: Int
    ): List<List<Number>> {
        return marketChartMapper.mapToDomain(apiService.getMarketChart(coinId, currency, days))
    }
}
