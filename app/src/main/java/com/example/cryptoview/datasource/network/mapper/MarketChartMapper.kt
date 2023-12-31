package com.example.cryptoview.datasource.network.mapper


import com.example.cryptoview.datasource.network.model.MarketChartObjectResponse
import javax.inject.Inject

class MarketChartMapper @Inject constructor() :
    EntityMapper<MarketChartObjectResponse, List<List<Number>>> {
    override fun mapToDomain(entity: MarketChartObjectResponse): List<List<Number>> {
        return entity.prices
    }
}
