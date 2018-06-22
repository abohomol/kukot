package com.abohomol.sdk.trading.model

import com.abohomol.sdk.network.BaseResponse

data class SpecificDealtOrdersResponse(val data: Data) : BaseResponse() {

    data class Data(
            val datas: List<SpecificDealtOrder>,
            val total: Int,
            val limit: Int,
            val pageNos: Int,
            val currPageNo: Int,
            val navigatePageNos: List<Int>,
            val userOid: String,
            val startRow: Int,
            val firstPage: Boolean,
            val lastPage: Boolean,
            val direction: String
    )
}

data class SpecificDealtOrder(
        val oid: String,
        val dealPrice: Double,
        val orderOid: String,
        val direction: String,
        val amount: Double,
        val dealValue: Double,
        val createdAt: Long
)