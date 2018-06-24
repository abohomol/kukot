package com.abohomol.kukot

import com.abohomol.kukot.asset.model.CoinBalance
import com.abohomol.kukot.asset.model.DepositAddress
import com.abohomol.kukot.asset.model.Record
import com.abohomol.kukot.asset.model.RecordStatus
import com.abohomol.kukot.currency.Currency
import com.abohomol.kukot.currency.ExchangeRate
import com.abohomol.kukot.language.UserLanguage
import com.abohomol.kukot.market.TradingSymbolsTick
import com.abohomol.kukot.network.CoinCode
import com.abohomol.kukot.network.CurrencyCode
import com.abohomol.kukot.network.LanguageCode
import com.abohomol.kukot.network.OrderId
import com.abohomol.kukot.trading.model.*
import com.abohomol.kukot.user.UserProfile
import io.reactivex.Completable
import io.reactivex.Single

interface KuCoinService {

    fun getUserProfile(): Single<UserProfile>

    fun getLanguages(): Single<List<UserLanguage>>

    fun changeLanguage(language: LanguageCode): Completable

    fun getCurrencies(): Single<List<Currency>>

    fun changeDefaultCurrency(currency: CurrencyCode): Completable

    fun getExchangeRates(vararg coins: CoinCode): Single<List<ExchangeRate>>

    fun getCoinDepositAddress(coin: CoinCode): Single<DepositAddress>

    fun withdrawalApply(coin: CoinCode, amount: Double, withdrawalAddress: String): Completable

    fun cancelWithdrawal(coin: CoinCode, transactionId: String): Completable

    fun getDepositRecords(coin: CoinCode, status: RecordStatus): Single<List<Record>>

    fun getWithdrawalRecords(coin: CoinCode, status: RecordStatus): Single<List<Record>>

    fun getCoinBalance(coin: CoinCode): Single<CoinBalance>

    fun getAllCoinBalances(page: Int, limit: Int): Single<List<CoinBalance>>

    fun createSellOrder(symbol: String, price: Double, amount: Double): Single<OrderId>

    fun createBuyOrder(symbol: String, price: Double, amount: Double): Single<OrderId>

    fun getActiveBuyOrders(symbol: String): Single<List<Order>>

    fun getActiveSellOrders(symbol: String): Single<List<Order>>

    fun cancelBuyOrder(symbol: String, orderOid: String): Completable

    fun cancelSellOrder(symbol: String, orderOid: String): Completable

    fun cancelAllOrders(symbol: String): Completable

    fun cancelAllBuyOrders(symbol: String): Completable

    fun cancelAllSellOrders(symbol: String): Completable

    fun getDealtOrders(type: OrderType,
                       since: Long,
                       before: Long,
                       symbol: String? = null): Single<List<MergedDealtOrder>>

    fun getDealtOrders(symbol: String, type: OrderType): Single<List<SpecificDealtOrder>>

    fun getOrderDetails(orderOid: String, symbol: String, type: OrderType): Single<OrderDetails>

    fun getTradingFavouriteSymbolsTick(market: String, symbol: String): Single<List<TradingSymbolsTick>>

    fun getTradingStickSymbolsTick(market: String, symbol: String): Single<List<TradingSymbolsTick>>

    fun getStickSymbols(): Single<List<String>>

    fun getFavouriteSymbols(): Single<List<String>>

    fun addFavouriteSymbol(symbol: String): Completable

    fun deleteFavouriteSymbol(symbol: String): Completable

    fun addStickSymbol(symbol: String): Completable

    fun deleteStickSymbol(symbol: String): Completable
}