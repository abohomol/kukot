package com.abohomol.sdk.currency

import com.abohomol.sdk.currency.model.CoinCode
import com.abohomol.sdk.currency.model.Currency
import com.abohomol.sdk.currency.model.CurrencyCode
import com.abohomol.sdk.currency.model.ExchangeRate
import io.reactivex.Completable
import io.reactivex.Single

interface CurrencyRepository {

    fun getCurrencies(): Single<List<Currency>>

    fun changeDefaultCurrency(currencyCode: CurrencyCode): Completable

    fun getExchangeRates(coins: List<CoinCode>): Single<List<ExchangeRate>>
}