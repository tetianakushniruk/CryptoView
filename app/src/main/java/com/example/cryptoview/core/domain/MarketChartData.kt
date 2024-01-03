package com.example.cryptoview.core.domain


data class MarketChartData(
    val prices: List<List<MarketChartItem>>
)


data class MarketChartItem(
    val timestamp: Long,
    val price: Float
)