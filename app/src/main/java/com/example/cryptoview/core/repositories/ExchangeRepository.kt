package com.example.cryptoview.core.repositories

import com.example.cryptoview.core.domain.ExchangeDomainModel
import com.example.cryptoview.core.remote.AppRemoteDataSourceImpl
import com.example.cryptoview.datasource.network.mapper.ExchangeMapper
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val networkDataSource: AppRemoteDataSourceImpl,
    private val exchange: ExchangeMapper
) {

    suspend fun fetchExchanges(): ExchangeDomainModel {
        return networkDataSource.fetchExchanges()
    }

}