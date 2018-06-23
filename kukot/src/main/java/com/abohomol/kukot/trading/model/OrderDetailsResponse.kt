package com.abohomol.kukot.trading.model

import com.abohomol.kukot.network.BaseResponse

data class OrderDetailsResponse(val data: OrderDetails) : BaseResponse()

data class OrderDetails(
        val coinType: String,
        val dealValueTotal: Double,
        val dealPriceAverage: Double,
        val feeTotal: Double,
        val userOid: String,
        val dealAmount: Double,
        val coinTypePair: String,
        val orderPrice: Double,
        val type: String,
        val orderOid: String,
        val pendingAmount: Double,
        val dealOrders: DealOrder
)

data class DealOrder(
        val total: Int,
        val firstPage: Boolean,
        val lastPage: Boolean,
        val currPageNo: Int,
        val limit: Int,
        val pageNos: Int,
        val datas: List<DealOrderChunk>
) {
    data class DealOrderChunk(
            val amount: Double,
            val dealValue: Double,
            val fee: Double,
            val dealPrice: Double,
            val feeRate: Double
    )
}