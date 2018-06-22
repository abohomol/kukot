package com.abohomol.sdk.currency

import com.abohomol.sdk.currency.model.Currency
import com.abohomol.sdk.currency.model.ExchangeRate
import com.abohomol.sdk.network.BaseRepository
import com.abohomol.sdk.network.CoinCode
import com.abohomol.sdk.network.CurrencyCode
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyRetrofitRepository(
        private val currencyService: CurrencyService,
        secret: String
) : BaseRepository(secret), CurrencyRepository {

    override fun getExchangeRates(coins: List<CoinCode>): Single<List<ExchangeRate>> {
        val query = coins.joinToString(separator = ",")
        return currencyService.getCurrenciesAndExchangeRates(query)
                .doOnSuccess { onResponse(it) }
                .map { it.exchangeRates() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCurrencies(): Single<List<Currency>> {
        return currencyService.getCurrenciesAndExchangeRates()
                .doOnSuccess { onResponse(it) }
                .map { it.currencies() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun changeDefaultCurrency(currencyCode: CurrencyCode): Completable {
        val headers = getHeaders(ENDPOINT,"currency=$currencyCode")
        return currencyService.changeCurrency(headers, ENDPOINT, currencyCode)
                .doOnSuccess { onResponse(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private const val ENDPOINT = "/v1/user/change-currency"
    }
}