package com.abohomol.kukot.info.model

import com.abohomol.kukot.network.BaseResponse

data class MarketTicksResponse(val data: List<MarketTick>) : BaseResponse()

data class MarketTick(
        val coinType: String,
        val trading: Boolean,
        val symbol: String,
        val lastDealPrice: Double,
        val buy: Double,
        val sell: Double,
        val change: Double,
        val coinTypePair: String,
        val sort: Double,
        val feeRate: Double,
        val volValue: Double,
        val vol: Double,
        val high: Double,
        val low: Double,
        val datetime: Long,
        val changeRate: Double
)