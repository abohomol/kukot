package com.abohomol.kukot.currency

import com.abohomol.kukot.currency.model.CurrencyResponse
import com.abohomol.kukot.network.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface CurrencyService {

    @GET("/v1/open/currencies")
    fun getCurrenciesAndExchangeRates(@Query("coins")
                                      coins: String? = null): Single<CurrencyResponse>

    @POST
    fun changeCurrency(@HeaderMap
                       headers: Map<String, String>,
                       @Url
                       url: String,
                       @Query("currency")
                       currency: String): Single<BaseResponse>
}