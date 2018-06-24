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

/**
 * Service, bounded to a particular user, which provides a certain amount of APIs
 * in order to enable access to KuCoin exchange.
 * Among the API groups are:
 * - User profile
 * - Language
 * - Currency
 * - Assets
 * - Trading
 * - Market
 */
interface KuCoinService {

    /**
     * Returns information about the user profile.
     */
    fun getUserProfile(): Single<UserProfile>

    /**
     * Returns information about supported languages, although some of them might not be available
     * at the moment.
     *
     * @see UserLanguage
     */
    fun getLanguages(): Single<List<UserLanguage>>

    /**
     * Changes default user language.
     *
     * @param language code of the desired language to set
     * @see LanguageCode
     */
    fun changeLanguage(language: LanguageCode): Completable

    /**
     * Returns information about available fiat currencies.
     */
    fun getCurrencies(): Single<List<Currency>>

    /**
     * Changes default user currency.
     *
     * @param currency code of the desired currency to set
     * @see CurrencyCode
     */
    fun changeCurrency(currency: CurrencyCode): Completable

    /**
     * Returns exchange rates for single coin or list of them.
     *
     * @param coins a single coin or list of coins to query
     * @see CoinCode
     */
    fun getExchangeRates(vararg coins: CoinCode): Single<List<ExchangeRate>>

    /**
     * Returns information about a deposit address for a specific coin.
     *
     * @param coin a coin to query
     * @see CoinCode
     */
    fun getCoinDepositAddress(coin: CoinCode): Single<DepositAddress>

    /**
     * Creates a withdrawal request for specific coin. Basically it requests to transfer a certain
     * amount of coins to a different address. Withdrawal request isn't immediate and might take
     * some time. Created withdrawal request could be canceled by calling [cancelWithdrawal].
     *
     * @param coin coin to withdraw
     * @param amount amount of coins to withdraw
     * @param withdrawalAddress address of the wallet or exchange where coins will be sent
     * @see CoinCode
     */
    fun withdrawCoin(coin: CoinCode, amount: Double, withdrawalAddress: String): Completable

    /**
     * Cancels withdrawal request created via [withdrawCoin]. Please, note that only requests
     * with PENDING status could be canceled.
     *
     * @param coin coin to cancel withdraw for
     * @param transactionId identifier of withdraw request, could be found via [getWithdrawalRecords]
     * @see CoinCode
     */
    fun cancelWithdrawal(coin: CoinCode, transactionId: String): Completable

    /**
     * Returns list of deposit records/transactions for a specific coin.
     *
     * @param coin coin to query
     * @param status status of the record to filter
     */
    fun getDepositRecords(coin: CoinCode, status: RecordStatus): Single<List<Record>>

    /**
     * Returns list of withdrawal records/transactions for a specific coin.
     *
     * @param coin coin to query
     * @param status status of the record to filter
     * @see CoinCode
     */
    fun getWithdrawalRecords(coin: CoinCode, status: RecordStatus): Single<List<Record>>

    /**
     * Returns balance of the specific coin.
     *
     * @param coin coin to query balance for
     * @see CoinCode
     */
    fun getCoinBalance(coin: CoinCode): Single<CoinBalance>

    /**
     * Returns all coin assets of the user.
     *
     * @param page page number
     * @param limit page limit，default 12，max 20
     */
    fun getAllCoinBalances(page: Int, limit: Int = 12): Single<List<CoinBalance>>

    /**
     * Creates SELL order for a specific coin pair. Order could be canceled by calling [cancelSellOrder]
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     * @param price order price per coin
     * @param amount amount of coins to sell
     */
    fun createSellOrder(symbol: String, price: Double, amount: Double): Single<OrderId>

    /**
     * Creates BUY order for a specific coin pair. Order could be canceled by calling [cancelBuyOrder]
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     * @param price order price per coin
     * @param amount amount of coins to buy
     */
    fun createBuyOrder(symbol: String, price: Double, amount: Double): Single<OrderId>

    /**
     * Returns active BUY orders for a specific coin pair.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun getActiveBuyOrders(symbol: String): Single<List<Order>>

    /**
     * Returns active SELL orders for a specific coin pair.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun getActiveSellOrders(symbol: String): Single<List<Order>>

    /**
     * Cancels BUY order to a specific coin pair by order identifier. Please, not that orders
     * could be canceled only if there were not executed.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     * @param orderOid order identifier, could be acquired via [getActiveBuyOrders]
     */
    fun cancelBuyOrder(symbol: String, orderOid: String): Completable

    /**
     * Cancels SELL order to a specific coin pair by order identifier. Please, not that orders
     * could be canceled only if there were not executed.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     * @param orderOid order identifier, could be acquired via [getActiveBuyOrders]
     */
    fun cancelSellOrder(symbol: String, orderOid: String): Completable

    /**
     * Cancels all orders for the specific trading pair. Please, not that orders
     * could be canceled only if there were not executed.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun cancelAllOrders(symbol: String): Completable

    /**
     * Cancels all BUY orders for the specific trading pair. Please, not that orders
     * could be canceled only if there were not executed.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun cancelAllBuyOrders(symbol: String): Completable

    /**
     * Cancels all SELL orders for the specific trading pair. Please, not that orders
     * could be canceled only if there were not executed.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun cancelAllSellOrders(symbol: String): Completable

    /**
     * Returns dealt BUY orders witch were already executed during a specific time interval.
     *
     * @param since timestamp
     * @param before timestamp
     * @param symbol trading symbol, e.g. KCS-BTC. If symbol is omitted all pairs will be returned
     */
    fun getDealtBuyOrders(since: Long, before: Long, symbol: String? = null): Single<List<MergedDealtOrder>>

    /**
     * Returns dealt SELL orders witch were already executed during a specific time interval.
     *
     * @param since timestamp
     * @param before timestamp
     * @param symbol trading symbol, e.g. KCS-BTC. If symbol is omitted all pairs will be returned
     */
    fun getDealtSellOrders(since: Long, before: Long, symbol: String? = null): Single<List<MergedDealtOrder>>

    /**
     * Returns dealt SELL orders for a specific trading pair.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun getDealtSellOrders(symbol: String): Single<List<SpecificDealtOrder>>

    /**
     * Returns dealt BUY orders for a specific trading pair.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun getDealtBuyOrders(symbol: String): Single<List<SpecificDealtOrder>>

    /**
     * Returns order details for a specific trading pair.
     *
     * @param orderOid order identifier, could be acquired by querying the list of dealt orders
     * @param symbol trading symbol, e.g. KCS-BTC
     * @param type order type to query
     */
    fun getOrderDetails(orderOid: String, symbol: String, type: OrderType): Single<OrderDetails>

    /**
     * Returns list of favourite symbols tick.
     *
     * @param market trading market, e.g. BTC, ETH, NEO.
     * @param symbol trading symbol, e.g. KCS-BTC
     * @see KuCoinInfoService
     */
    fun getTradingFavouriteSymbolsTick(market: String, symbol: String): Single<List<TradingSymbolsTick>>

    /**
     * Returns list of stick symbols tick.
     *
     * @param market trading market, e.g. BTC, ETH, NEO.
     * @param symbol trading symbol, e.g. KCS-BTC
     * @see KuCoinInfoService
     */
    fun getTradingStickSymbolsTick(market: String, symbol: String): Single<List<TradingSymbolsTick>>

    /**
     * Returns list of stick symbols. You can add stick symbols via [addStickSymbol] and remove
     * them via [deleteStickSymbol].
     */
    fun getStickSymbols(): Single<List<String>>

    /**
     * Returns list of favourite symbols. You can add favourite symbols via [addFavouriteSymbol]
     * and remove them via [deleteFavouriteSymbol].
     */
    fun getFavouriteSymbols(): Single<List<String>>

    /**
     * Adds favourite symbol.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun addFavouriteSymbol(symbol: String): Completable

    /**
     * Deletes favourite symbol.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun deleteFavouriteSymbol(symbol: String): Completable

    /**
     * Adds stick symbol.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun addStickSymbol(symbol: String): Completable

    /**
     * Deletes stick symbol.
     *
     * @param symbol trading symbol, e.g. KCS-BTC
     */
    fun deleteStickSymbol(symbol: String): Completable
}