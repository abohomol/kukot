package com.abohomol.kukot.market

import com.abohomol.kukot.network.BaseResponse
import com.google.gson.annotations.SerializedName

data class TradingSymbolsTickResponse(val data: List<TradingSymbolsTick>) : BaseResponse()

data class TradingSymbolsTick(
        val coinType: String,
        val trading: Boolean,
        val lastDealPrice: Double,
        val buy: Double,
        val sell: Double,
        val coinTypePair: String,
        val sort: Double,
        val feeRate: Double,
        val volValue: Double,
        val high: Double,
        val datetime: Long,
        val vol: Double,
        val low: Double,
        val changeRate: Double,
        val change: Double,
        val stick: Boolean,
        @SerializedName("fav")
        val favourite: Boolean
)

data class SymbolsResponse(val data: List<String>): BaseResponse()

enum class SymbolType {
    FAVOURITE,
    STICK
}