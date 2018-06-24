package com.abohomol.kukot.info.model

import com.abohomol.kukot.network.BaseResponse

data class TradingMarketsResponse(val data: List<String>) : BaseResponse()