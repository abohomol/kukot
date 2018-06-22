package com.abohomol.sdk

import com.abohomol.sdk.asset.model.CoinBalance
import com.abohomol.sdk.asset.model.DepositAddress
import com.abohomol.sdk.asset.model.Record
import com.abohomol.sdk.asset.model.RecordStatus
import com.abohomol.sdk.asset.model.RecordType
import com.abohomol.sdk.currency.model.Currency
import com.abohomol.sdk.currency.model.ExchangeRate
import com.abohomol.sdk.language.model.UserLanguage
import com.abohomol.sdk.network.CoinCode
import com.abohomol.sdk.network.CurrencyCode
import com.abohomol.sdk.network.LanguageCode
import com.abohomol.sdk.trading.model.*
import com.abohomol.sdk.user.model.UserProfile
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

    fun createOrder(symbol: String, type: OrderType, price: Double, amount: Double): Single<String>

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
}