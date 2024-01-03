package com.thecode.cryptoview.core.remote

import com.thecode.cryptoview.core.domain.CoinDomainModel
import com.thecode.cryptoview.core.domain.ExchangeDomainModel
import com.thecode.cryptoview.datasource.CoinGeckoApiRemoteService
import com.thecode.cryptoview.datasource.network.mapper.CoinMapper
import com.thecode.cryptoview.datasource.network.mapper.ExchangeMapper
import com.thecode.cryptoview.datasource.network.mapper.MarketChartMapper
import javax.inject.Inject

interface AppRemoteDataSource {

    suspend fun fetchCoins(currency: String): CoinDomainModel

    suspend fun fetchCoinById(coinId: String): CoinDomainModel

    suspend fun fetchExchanges(): ExchangeDomainModel

    suspend fun fetchMarketChartData(
        coinId: String,
        currency: String,
        days: Int
    ): List<List<Number>>
}

class AppRemoteDataSourceImpl @Inject constructor(
    private val apiService: CoinGeckoApiRemoteService,
    private val coinMapper: CoinMapper,
    private val exchangeMapper: ExchangeMapper,
    private val marketChartMapper: MarketChartMapper

) : AppRemoteDataSource {

    override suspend fun fetchCoins(currency: String): CoinDomainModel {
        return coinMapper.mapFromList(apiService.getAllCoins(currency))
    }

    override suspend fun fetchCoinById(coinId: String): CoinDomainModel {
        TODO("Not yet implemented")
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
