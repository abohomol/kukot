package com.abohomol.kukot

import com.abohomol.kukot.info.model.CoinInfo
import com.abohomol.kukot.info.model.MarketTick
import com.abohomol.kukot.network.CoinCode
import io.reactivex.Single

/**
 * Information service provides basic information about the market and has open API which
 * means that no API Key/Secret needed to access this data.
 */
interface KuCoinInfoService {

    /**
     * Returns market information about all coins available on the exchange.
     */
    fun getMarketTicks(): Single<List<MarketTick>>

    /**
     * Returns all markets available for trading on the exchange. (e.g. BTC, NEO, KCS, etc.)
     */
    fun getTradingMarkets(): Single<List<String>>

    /**
     * Returns information about a particular coin.
     * @param coin the short code of the coin to query
     */
    fun getCoinInfo(coin: CoinCode): Single<CoinInfo>

    /**
     * Returns information about all coins available on the exchange.
     */
    fun getCoinsInfo(): Single<List<CoinInfo>>
}