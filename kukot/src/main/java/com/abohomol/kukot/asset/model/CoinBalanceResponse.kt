package com.abohomol.kukot.asset.model

import com.abohomol.kukot.network.BaseResponse
import com.abohomol.kukot.network.CoinCode

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