package com.abohomol.kukot.market

import com.abohomol.kukot.network.BaseRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitMarketRepository(
        private val marketService: MarketService,
        secret: String
) : BaseRepository(secret) {

    fun getTradingFavouriteSymbolsTick(market: String,
                                       symbol: String): Single<List<TradingSymbolsTick>> {
        return getTradingSymbolsTick(market, symbol, SymbolType.FAVOURITE)
    }

    fun getTradingStickSymbolsTick(market: String,
                                   symbol: String): Single<List<TradingSymbolsTick>> {
        return getTradingSymbolsTick(market, symbol, SymbolType.STICK)
    }

    fun getStickSymbols() = getSymbols("/v1/market/stick-symbols")

    fun getFavouriteSymbols() = getSymbols("/v1/market/fav-symbols")

    fun addFavouriteSymbol(symbol: String) = updateSymbolByType(symbol, "fav", true)

    fun deleteFavouriteSymbol(symbol: String) = updateSymbolByType(symbol, "fav", false)

    fun addStickSymbol(symbol: String) = updateSymbolByType(symbol, "stick", true)

    fun deleteStickSymbol(symbol: String) = updateSymbolByType(symbol, "stick", false)

    private fun getTradingSymbolsTick(market: String,
                                      symbol: String,
                                      filter: SymbolType): Single<List<TradingSymbolsTick>> {
        val endpoint = "/v1/market/symbols"
        val query = "filter=${filter.name}&market=$market&symbol=$symbol"
        val headers = getHeaders(endpoint, query)
        val queries = mapOf(
                "filter" to filter.name,
                "market" to market,
                "symbol" to symbol
        )
        return marketService.getTradingSymbolsTick(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun updateSymbolByType(symbol: String, type: String, add: Boolean): Completable {
        val endpoint = "/v1/market/symbol/$type"
        val query = "$type=${if (add) 1 else 0}&symbol=$symbol"
        val headers = getHeaders(endpoint, query)
        val queries = mapOf(
                type to "${if (add) 1 else 0}",
                "symbol" to symbol
        )
        return marketService.updateStickOrFavouriteSymbol(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getSymbols(endpoint: String): Single<List<String>> {
        val headers = getHeaders(endpoint, "")
        return marketService.getSymbols(headers, endpoint)
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}