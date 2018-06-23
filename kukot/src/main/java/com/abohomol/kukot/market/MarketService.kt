package com.abohomol.kukot.market

import com.abohomol.kukot.network.BaseResponse
import io.reactivex.Single
import retrofit2.http.*

interface MarketService {

    @GET
    fun getTradingSymbolsTick(@HeaderMap
                              headers: Map<String, String>,
                              @Url
                              url: String,
                              @QueryMap
                              queries: Map<String, String>): Single<TradingSymbolsTickResponse>

    @GET
    fun getSymbols(@HeaderMap
                   headers: Map<String, String>,
                   @Url
                   url: String): Single<SymbolsResponse>

    @POST
    fun updateStickOrFavouriteSymbol(@HeaderMap
                                     headers: Map<String, String>,
                                     @Url
                                     url: String,
                                     @QueryMap
                                     queries: Map<String, String>): Single<BaseResponse>
}