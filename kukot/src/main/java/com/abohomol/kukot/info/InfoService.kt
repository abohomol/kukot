package com.abohomol.kukot.info

import com.abohomol.kukot.info.model.CoinInfoListResponse
import com.abohomol.kukot.info.model.CoinInfoResponse
import com.abohomol.kukot.info.model.MarketTicksResponse
import com.abohomol.kukot.info.model.TradingMarketsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface InfoService {

    @GET("/v1/open/tick")
    fun getMarketTicks(): Single<MarketTicksResponse>

    @GET("/v1/open/markets")
    fun getTradingMarkets(): Single<TradingMarketsResponse>

    @GET("/v1/market/open/coin-info")
    fun getCoinInfo(@Query("coin") coin: String): Single<CoinInfoResponse>

    @GET("/v1/market/open/coins")
    fun getCoinsInfo(): Single<CoinInfoListResponse>
}