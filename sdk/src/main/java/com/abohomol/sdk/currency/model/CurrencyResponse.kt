package com.abohomol.sdk.currency.model

import com.abohomol.sdk.network.BaseResponse
import com.abohomol.sdk.network.CoinCode
import com.abohomol.sdk.network.CurrencyCode

data class CurrencyResponse(val data: Data) : BaseResponse() {

    fun currencies() = data.currencies.map { Currency(it[0] as String, it[1] as String) }

    fun exchangeRates() = data.rates.map { entry -> ExchangeRate(entry.key, entry.value) }
}

data class Data(val currencies: List<List<Any>>, val rates: Map<CoinCode, Map<CurrencyCode, Double>>)

data class Currency(val code: CurrencyCode, val symbol: String)

data class ExchangeRate(val coinCode: CoinCode, val rates: Map<CurrencyCode, Double>)