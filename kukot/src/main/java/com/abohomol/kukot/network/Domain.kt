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

/**
 * The alias for a fiat currency code (e.g. USD, EUR, AUD)
 */
typealias CurrencyCode = String

/**
 * The alias for a crypto currency code (e.g. BTC, KCS, ETH)
 */
typealias CoinCode = String

/**
 * The alias for a language code (e.g. en_US, zh_CN)
 */
typealias LanguageCode = String

/**
 * The alias for an order identifier
 */
typealias OrderId = String