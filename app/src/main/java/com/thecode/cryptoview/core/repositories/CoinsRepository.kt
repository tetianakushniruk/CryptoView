package com.thecode.cryptoview.core.repositories

import com.thecode.cryptoview.core.domain.CoinDomainModel
import com.thecode.cryptoview.core.remote.AppRemoteDataSourceImpl
import com.thecode.cryptoview.datasource.network.mapper.CoinMapper
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val networkDataSource: AppRemoteDataSourceImpl,
    private val coinMapper: CoinMapper
) {

    suspend fun fetchCoins(currency: String): CoinDomainModel {
        return networkDataSource.fetchCoins(currency)
    }

    suspend fun fetchCoinById(id: String): CoinDomainModel {
        return networkDataSource.fetchCoinById(id)
    }
}