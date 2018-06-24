package com.abohomol.kukot.info.model

import com.abohomol.kukot.network.BaseResponse

data class CoinInfoResponse(val data: CoinInfo) : BaseResponse()

data class CoinInfoListResponse(val data: List<CoinInfo>) : BaseResponse()

data class CoinInfo(
    val withdrawMinFee: Double,
    val coinType: String,
    val withdrawMinAmount: Double,
    val withdrawRemark: String,
    val orgAddress: String,
    val txUrl: String,
    val userAddressName: String,
    val withdrawFeeRate: Double,
    val confirmationCount: Int,
    val infoUrl: String,
    val enable: Boolean,
    val name: String,
    val tradePrecision: Int,
    val depositRemark: String,
    val enableWithdraw: Boolean,
    val enableDeposit: Boolean,
    val coin: String
)