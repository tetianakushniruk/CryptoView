package com.thecode.cryptoview.core.repositories

import com.thecode.cryptoview.core.domain.ExchangeDomainModel
import com.thecode.cryptoview.core.remote.AppRemoteDataSourceImpl
import com.thecode.cryptoview.datasource.network.mapper.ExchangeMapper
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val networkDataSource: AppRemoteDataSourceImpl,
    private val exchange: ExchangeMapper
) {

    suspend fun fetchExchanges(): ExchangeDomainModel {
        return networkDataSource.fetchExchanges()
    }

}