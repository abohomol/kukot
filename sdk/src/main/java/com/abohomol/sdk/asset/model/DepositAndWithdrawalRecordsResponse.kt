package com.abohomol.sdk.asset.model

import com.abohomol.sdk.network.BaseResponse

data class DepositAndWithdrawalRecordsResponse(val data: Data): BaseResponse()

data class Data(
        val datas: List<Record>,
        val total: Int,
        val limit: Int,
        val pageNos: Int,
        val currPageNo: Int,
        val navigatePageNos: List<Int>,
        val coinType: String,
        val type: String,
        val userOid: String,
        val status: String,
        val firstPage: Boolean,
        val lastPage: Boolean,
        val startRow: Int
)

data class Record(
    val fee: Double,
    val oid: String,
    val type: String,
    val amount: Double,
    val remark: String,
    val status: String,
    val address: String,
    val context: String,
    val userOid: String,
    val coinType: String,
    val createdAt: Long,
    val deletedAt: Long,
    val updatedAt: Long,
    val outerWalletTxid: String
)

enum class RecordType {
    DEPOSIT,
    WITHDRAW
}

enum class RecordStatus {
    FINISHED,
    PENDING,
    CANCEL
}