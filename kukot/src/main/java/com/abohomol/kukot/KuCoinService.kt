package com.abohomol.kukot

import com.abohomol.kukot.asset.model.CoinBalance
import com.abohomol.kukot.asset.model.DepositAddress
import com.abohomol.kukot.asset.model.Record
import com.abohomol.kukot.asset.model.RecordStatus
import com.abohomol.kukot.asset.model.RecordType
import com.abohomol.kukot.currency.model.Currency
import com.abohomol.kukot.currency.model.ExchangeRate
import com.abohomol.kukot.language.model.UserLanguage
import com.abohomol.kukot.market.TradingSymbolsTick
import com.abohomol.kukot.network.CoinCode
import com.abohomol.kukot.network.CurrencyCode
import com.abohomol.kukot.network.LanguageCode
import com.abohomol.kukot.network.OrderId
import com.abohomol.kukot.trading.model.*
import com.abohomol.kukot.user.model.UserProfile
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

    fun getDepositAndWithdrawalRecords(coin: CoinCode, type: RecordType, status: RecordStatus, page: Int = 0): Single<List<Record>>

    fun getCoinBalance(coin: CoinCode): Single<CoinBalance>

    fun getCoinBalanceByPage(coin: CoinCode, page: Int, limit: Int): Single<List<CoinBalance>>

    fun createOrder(symbol: String, type: OrderType, price: Double, amount: Double): Single<OrderId>

    fun getActiveOrders(symbol: String, type: OrderType): Single<List<Order>>

    fun cancelOrder(symbol: String, type: OrderType, orderOid: String): Completable

    fun cancelAllOrders(symbol: String, type: OrderType): Completable

    fun getMergedDealtOrders(type: OrderType,
                             limit: Int,
                             page: Int,
                             since: Long,
                             before: Long,
                             symbol: String? = null): Single<List<MergedDealtOrder>>

    fun getSpecificDealtOrders(symbol: String,
                               type: OrderType,
                               limit: Int,
                               page: Int): Single<List<SpecificDealtOrder>>

    fun getOrderDetails(orderOid: String,
                        symbol: String,
                        type: OrderType,
                        limit: Int,
                        page: Int): Single<OrderDetails>

    fun getTradingFavouriteSymbolsTick(market: String, symbol: String): Single<List<TradingSymbolsTick>>

    fun getTradingStickSymbolsTick(market: String, symbol: String): Single<List<TradingSymbolsTick>>

    fun getStickSymbols(): Single<List<String>>

    fun getFavouriteSymbols(): Single<List<String>>

    fun addFavouriteSymbol(symbol: String): Completable

    fun deleteFavouriteSymbol(symbol: String): Completable

    fun addStickSymbol(symbol: String): Completable

    fun deleteStickSymbol(symbol: String): Completable
}