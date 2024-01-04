package com.example.cryptoview.core.repositories

import com.example.cryptoview.core.domain.CoinDomainModel
import com.example.cryptoview.core.remote.DefaultRemoteDataSource
import com.example.cryptoview.datasource.network.mapper.CoinMapper
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val networkDataSource: DefaultRemoteDataSource,
) {

    suspend fun fetchCoins(currency: String): CoinDomainModel {
        return networkDataSource.fetchCoins(currency)
    }
}