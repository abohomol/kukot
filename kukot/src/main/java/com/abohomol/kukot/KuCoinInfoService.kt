package com.abohomol.kukot

import com.abohomol.kukot.info.model.CoinInfo
import com.abohomol.kukot.info.model.MarketTick
import com.abohomol.kukot.network.CoinCode
import io.reactivex.Single

interface KuCoinInfoService {

    fun getMarketTicks(): Single<List<MarketTick>>

    fun getTradingMarkets(): Single<List<String>>

    fun getCoinInfo(coin: CoinCode): Single<CoinInfo>

    fun getCoinsInfo(): Single<List<CoinInfo>>
}