package com.abohomol.sdk.asset.model

import com.abohomol.sdk.network.BaseResponse

data class DepositAddressResponse(val data: DepositAddress) : BaseResponse()

data class DepositAddress(
        val oid: String,
        val address: String,
        val context: String,
        val userOid: String,
        val coinType: String,
        val createdAt: Long,
        val deletedAt: Long,
        val updatedAt: Long,
        val lastReceivedAt: Long
)