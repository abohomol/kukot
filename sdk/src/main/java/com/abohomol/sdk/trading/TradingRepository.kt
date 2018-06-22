package com.abohomol.sdk.trading

import com.abohomol.sdk.trading.model.*
import io.reactivex.Completable
import io.reactivex.Single

interface TradingRepository {

    fun createOrder(symbol: String, type: OrderType, price: Double, amount: Double): Single<String>

    fun getActiveOrders(symbol: String, type: OrderType): Single<List<Order>>

    fun cancelOrder(symbol: String, type: OrderType, orderOid: String): Completable

    fun cancelAllOrders(symbol: String, type: OrderType): Completable

    fun getMergedDealtOrders(symbol: String? = null,
                             type: OrderType,
                             limit: Int,
                             page: Int,
                             since: Long,
                             before: Long): Single<List<MergedDealtOrder>>

    fun getSpecificDealtOrders(symbol: String,
                               type: OrderType,
                               limit: Int,
                               page: Int): Single<List<SpecificDealtOrder>>

    fun getOrderDetails(orderOid: String,
                        symbol: String,
                        type: OrderType,
                        limit: Int,
                        page: Int): Single<OrderDetails>
}