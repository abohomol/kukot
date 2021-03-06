package com.abohomol.kukot.trading

import com.abohomol.kukot.network.BaseRepository
import com.abohomol.kukot.network.OrderId
import com.abohomol.kukot.trading.model.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitTradingRepository(
        private val tradingService: TradingService,
        secret: String
) : BaseRepository(secret) {

    fun createOrder(symbol: String,
                    type: OrderType,
                    price: Double,
                    amount: Double): Single<OrderId> {
        val endpoint = "/v1/order"
        val query = "amount=$amount&price=$price&symbol=$symbol&type=${type.name}"
        val headers = getHeaders(endpoint, query)
        val queries = mapOf(
                "amount" to amount.toString(),
                "price" to price.toString(),
                "symbol" to symbol,
                "type" to type.name
        )
        return tradingService.createOrder(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .map { it.data.orderOid }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getActiveOrders(symbol: String, type: OrderType): Single<List<Order>> {
        val endpoint = "/v1/order/active-map"
        val query = "symbol=$symbol&type=${type.name}"
        val headers = getHeaders(endpoint, query)
        val queries = mapOf(
                "symbol" to symbol,
                "type" to type.name
        )
        return tradingService.getActiveOrders(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .map { if (type == OrderType.SELL) it.data.sell else it.data.buy }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun cancelOrder(symbol: String, type: OrderType, orderOid: String): Completable {
        val endpoint = "/v1/cancel-order"
        val query = "orderOid=$orderOid&symbol=$symbol&type=${type.name}"
        val headers = getHeaders(endpoint, query)
        val queries = mapOf(
                "orderOid" to orderOid,
                "symbol" to symbol,
                "type" to type.name
        )
        return tradingService.cancelOrder(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun cancelAllOrders(symbol: String, type: OrderType? = null): Completable {
        val endpoint = "/v1/order/cancel-all"
        val orderTypeQ = if (type == null) "" else "&type=${type.name}"
        val query = "symbol=$symbol$orderTypeQ"
        val headers = getHeaders(endpoint, query)
        val queries = mutableMapOf("symbol" to symbol)
        type?.let { queries["type"] = type.name }
        return tradingService.cancelAllOrders(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMergedDealtOrders(symbol: String?,
                             type: OrderType,
                             since: Long,
                             before: Long): Single<List<MergedDealtOrder>> {
        val endpoint = "/v1/order/dealt"
        val symbolQuery = if (symbol == null) "" else "&symbol=$symbol"
        val query = "before=$before&since=$since$symbolQuery&type=${type.name}"
        val queries = mutableMapOf(
                "before" to before.toString(),
                "since" to since.toString(),
                "type" to type.name
        )
        symbol?.let { queries["symbol"] = it }
        val headers = getHeaders(endpoint, query)
        return tradingService.getMergedDealtOrders(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .map { it.data.datas }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSpecificDealtOrders(symbol: String, type: OrderType): Single<List<SpecificDealtOrder>> {
        val endpoint = "/v1/deal-orders"
        val query = "symbol=$symbol&type=${type.name}"
        val queries = mutableMapOf(
                "symbol" to symbol,
                "type" to type.name
        )
        val headers = getHeaders(endpoint, query)
        return tradingService.getSpecificDealtOrders(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .map { it.data.datas }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getOrderDetails(orderOid: String, symbol: String, type: OrderType): Single<OrderDetails> {
        val endpoint = "/v1/order/detail"
        val query = "orderOid=$orderOid&symbol=$symbol&type=${type.name}"
        val queries = mutableMapOf(
                "orderOid" to orderOid,
                "symbol" to symbol,
                "type" to type.name
        )
        val headers = getHeaders(endpoint, query)
        return tradingService.getOrderDetails(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}