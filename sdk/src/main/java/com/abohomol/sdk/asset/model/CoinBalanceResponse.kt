package com.abohomol.sdk.asset.model

import com.abohomol.sdk.network.BaseResponse
import com.abohomol.sdk.network.CoinCode

data class CoinBalanceResponse(val data: CoinBalance): BaseResponse()

data class CoinBalancePageResponse(val data: CoinBalancePageData): BaseResponse()

data class CoinBalancePageData(
        val total: Int,
        val datas: List<CoinBalance>,
        val currPageNo: Int,
        val limit: Int,
        val pageNos: Int
)

data class CoinBalance(val coinType: CoinCode, val balance: Double, val freezeBalance: Double)