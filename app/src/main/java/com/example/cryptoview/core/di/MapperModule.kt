package com.example.cryptoview.core.di

import com.example.cryptoview.core.domain.CoinItemDomainModel
import com.example.cryptoview.core.domain.ExchangeItemDomainModel
import com.example.cryptoview.datasource.network.mapper.CoinMapper
import com.example.cryptoview.datasource.network.mapper.EntityMapper
import com.example.cryptoview.datasource.network.mapper.ExchangeMapper
import com.example.cryptoview.datasource.network.mapper.MarketChartMapper
import com.example.cryptoview.datasource.network.model.CoinObjectResponse
import com.example.cryptoview.datasource.network.model.ExchangeObjectResponse
import com.example.cryptoview.datasource.network.model.MarketChartObjectResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun provideCoinResponseMapper(): EntityMapper<CoinObjectResponse, CoinItemDomainModel> {
        return CoinMapper()
    }

    @Singleton
    @Provides
    fun provideExchangeResponseMapper(): EntityMapper<ExchangeObjectResponse, ExchangeItemDomainModel> {
        return ExchangeMapper()
    }

    @Singleton
    @Provides
    fun provideMarketChartResponseMapper(): EntityMapper<MarketChartObjectResponse, List<List<Number>>> {
        return MarketChartMapper()
    }
}
