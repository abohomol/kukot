package com.abohomol.sdk.network

open class BaseResponse {

    var code: String = ""
    var msg: String = ""
    var success: Boolean = false
    var timestamp: Long = 0
}

class NotSuccessfulRequestException(private val baseResponse: BaseResponse) : Exception() {

    override val message: String?
        get() = "Code: ${baseResponse.code}, timestamp: ${baseResponse.timestamp}, message: ${baseResponse.msg}"
}

typealias CurrencyCode = String

typealias CoinCode = String

typealias LanguageCode = String