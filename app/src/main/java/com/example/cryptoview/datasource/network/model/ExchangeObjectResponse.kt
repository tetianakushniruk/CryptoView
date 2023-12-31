package com.example.cryptoview.datasource.network.model

import com.google.gson.annotations.SerializedName

class ExchangeObjectResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("country")
    var country: String? = null,

    @SerializedName("year_established")
    var year_established: Int? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("image")
    val image: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("has_trading_incentive")
    var has_trading_incentive: Boolean? = null,

    @SerializedName("trust_score_rank")
    val trust_score_rank: Int,

    @SerializedName("trust_score")
    val trust_score: Int,

    @SerializedName("trade_volume_24h_btc")
    val trade_volume_24h_btc: Float,

    @SerializedName("trade_volume_24h_btc_normalized")
    val trade_volume_24h_btc_normalized: Float
)