package com.abohomol.kukot.trading.model

import com.abohomol.kukot.network.BaseResponse

data class MergedDealtOrdersResponse(val data: Data) : BaseResponse() {

    data class Data(
            val total: Int,
            val limit: Int,
            val page: Int,
            val datas: List<MergedDealtOrder>
    )
}

data class MergedDealtOrder(
        val createdAt: Long,
        val amount: Double,
        val dealValue: Double,
        val dealPrice: Double,
        val fee: Double,
        val feeRate: Double,
        val oid: String,
        val orderOid: String,
        val coinType: String,
        val coinTypePair: String,
        val direction: String,
        val dealDirection: String
)

