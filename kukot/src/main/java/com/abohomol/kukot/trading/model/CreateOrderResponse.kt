package com.abohomol.kukot.trading.model

import com.abohomol.kukot.network.BaseResponse

data class CreateOrderResponse(val data: Data) : BaseResponse() {

    data class Data(val orderOid: String)
}
