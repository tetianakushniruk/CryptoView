package com.example.cryptoview.datasource.network.model

import com.google.gson.annotations.SerializedName


class CoinObjectResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("current_price")
    val current_price: Float,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("high_24h")
    val high_24h: Float,

    @SerializedName("low_24h")
    val low_24h: Float,

    @SerializedName("market_cap")
    val market_cap: Float,

    @SerializedName("market_cap_rank")
    val market_cap_rank: Int,

    @SerializedName("fully_diluted_valuation")
    var fully_diluted_valuation: Float? = null,

    @SerializedName("price_change_24h")
    val price_change_24h: Float,

    @SerializedName("total_volume")
    val total_volume: Float,

    @SerializedName("price_change_percentage_24h")
    val price_change_percentage_24h: Float,

    @SerializedName("market_cap_change_24h")
    val market_cap_change_24h: Float,

    @SerializedName("market_cap_change_percentage_24h")
    val market_cap_change_percentage_24h: Float,

    @SerializedName("max_supply")
    val max_supply: Float,

    @SerializedName("ath")
    val ath: Float,


    )
