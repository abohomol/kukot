package com.abohomol.sdk.trading.model

import com.abohomol.sdk.network.BaseResponse

data class CreateOrderResponse(val data: Data) : BaseResponse() {

    data class Data(val orderOid: String)
}
