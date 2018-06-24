package com.abohomol.kukot.network

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("_comment")
    var comment: String = ""
    var code: String = ""
    var msg: String = ""
    var success: Boolean = false
    var timestamp: Long = 0
}

class KuCoinRequestError(private val base: BaseResponse) : Exception() {

    override val message: String?
        get() = "Code: ${base.code}, timestamp: ${base.timestamp}, message: ${base.msg}, comment: ${base.comment}"
}

typealias CurrencyCode = String

typealias CoinCode = String

typealias LanguageCode = String

typealias OrderId = String