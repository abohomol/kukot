package com.abohomol.sdk.currency

import com.abohomol.sdk.currency.model.CoinCode
import com.abohomol.sdk.currency.model.Currency
import com.abohomol.sdk.currency.model.CurrencyCode
import com.abohomol.sdk.currency.model.ExchangeRate
import com.abohomol.sdk.network.BaseRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyRetrofitRepository(
        private val currencyService: CurrencyService,
        secret: String
) : BaseRepository(secret), CurrencyRepository {

    override fun getCurrencies(): Single<List<Currency>> {
        return currencyService.getCurrenciesAndExchangeRates()
                .doOnSuccess { onResponse(it) }
                .map { it.currencies() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun changeDefaultCurrency(currencyCode: CurrencyCode): Completable {
        val headers = getHeaders("currency=$currencyCode")
        return currencyService.changeCurrency(headers, endpoint(), currencyCode)
                .doOnSuccess { onResponse(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getExchangeRates(coins: List<CoinCode>): Single<List<ExchangeRate>> {
        val query = coins.joinToString(separator = ",")
        return currencyService.getCurrenciesAndExchangeRates(query)
                .doOnSuccess { onResponse(it) }
                .map { it.exchangeRates() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun endpoint() = "/v1/user/change-currency"
}