package com.abohomol.kukot.currency

import com.abohomol.kukot.currency.model.Currency
import com.abohomol.kukot.currency.model.ExchangeRate
import com.abohomol.kukot.network.CoinCode
import com.abohomol.kukot.network.CurrencyCode
import io.reactivex.Completable
import io.reactivex.Single

interface CurrencyRepository {

    fun getCurrencies(): Single<List<Currency>>

    fun changeDefaultCurrency(currencyCode: CurrencyCode): Completable

    fun getExchangeRates(coins: List<CoinCode>): Single<List<ExchangeRate>>
}