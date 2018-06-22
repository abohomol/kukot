package com.abohomol.sdk.trading.model

import com.abohomol.sdk.network.BaseResponse
import com.google.gson.annotations.SerializedName

data class ActiveOrdersResponse(val data: Data) : BaseResponse() {

    data class Data(
            @SerializedName("SELL")
            val sell: List<Order>,
            @SerializedName("BUY")
            val buy: List<Order>
    )
}

data class Order(
        val oid: String,
        val type: String,
        val userOid: String,
        val coinType: String,
        val coinTypePair: String,
        val direction: String,
        val price: Double,
        val dealAmount: Double,
        val pendingAmount: Double,
        val createdAt: Long,
        val updatedAt: Long
)

/*
 * Also known as direction or deal direction.
 */
enum class OrderType {
    BUY,
    SELL
}