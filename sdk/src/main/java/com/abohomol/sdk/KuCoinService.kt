package com.abohomol.sdk

import com.abohomol.sdk.currency.model.CoinCode
import com.abohomol.sdk.currency.model.Currency
import com.abohomol.sdk.currency.model.CurrencyCode
import com.abohomol.sdk.currency.model.ExchangeRate
import com.abohomol.sdk.language.model.UserLanguage
import com.abohomol.sdk.user.model.UserProfile
import io.reactivex.Completable
import io.reactivex.Single

interface KuCoinService {

    fun getUserProfile(): Single<UserProfile>

    fun getLanguages(): Single<List<UserLanguage>>

    fun changeLanguage(language: String): Completable

    fun getCurrencies(): Single<List<Currency>>

    fun changeDefaultCurrency(currency: CurrencyCode): Completable

    fun getExchangeRates(vararg coins: CoinCode): Single<List<ExchangeRate>>
}