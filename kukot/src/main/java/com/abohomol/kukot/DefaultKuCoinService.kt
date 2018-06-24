package com.abohomol.kukot

import com.abohomol.kukot.asset.RetrofitAssetRepository
import com.abohomol.kukot.asset.model.CoinBalance
import com.abohomol.kukot.asset.model.Record
import com.abohomol.kukot.asset.model.RecordStatus
import com.abohomol.kukot.asset.model.RecordType
import com.abohomol.kukot.currency.RetrofitCurrencyRepository
import com.abohomol.kukot.language.RetrofitLanguageRepository
import com.abohomol.kukot.market.RetrofitMarketRepository
import com.abohomol.kukot.market.TradingSymbolsTick
import com.abohomol.kukot.network.CoinCode
import com.abohomol.kukot.network.CurrencyCode
import com.abohomol.kukot.network.LanguageCode
import com.abohomol.kukot.network.OrderId
import com.abohomol.kukot.trading.RetrofitTradingRepository
import com.abohomol.kukot.trading.model.*
import com.abohomol.kukot.user.RetrofitUserProfileRepository
import io.reactivex.Completable
import io.reactivex.Single

class DefaultKuCoinService(
        private val userProfileRepository: RetrofitUserProfileRepository,
        private val languageRepository: RetrofitLanguageRepository,
        private val currencyRepository: RetrofitCurrencyRepository,
        private val assetRepository: RetrofitAssetRepository,
        private val tradingRepository: RetrofitTradingRepository,
        private val marketRepository: RetrofitMarketRepository
) : KuCoinService {

    override fun getUserProfile() = userProfileRepository.getUserProfile()

    override fun getLanguages() = languageRepository.getLanguages()

    override fun changeLanguage(language: LanguageCode) = languageRepository.changeLanguage(language)

    override fun getCurrencies() = currencyRepository.getCurrencies()

    override fun changeDefaultCurrency(currency: CurrencyCode) = currencyRepository.changeCurrency(currency)

    override fun getExchangeRates(vararg coins: CoinCode) = currencyRepository.getExchangeRates(coins.asList())

    override fun getCoinDepositAddress(coin: CoinCode) = assetRepository.getDepositAddress(coin)

    override fun withdrawalApply(coin: CoinCode,
                                 amount: Double,
                                 withdrawalAddress: String): Completable {
        return assetRepository.withdraw(coin, amount, withdrawalAddress)
    }

    override fun cancelWithdrawal(coin: CoinCode, transactionId: String): Completable {
        return assetRepository.cancelWithdrawal(coin, transactionId)
    }

    override fun getDepositRecords(coin: CoinCode, status: RecordStatus): Single<List<Record>> {
        return assetRepository.getDepositAndWithdrawalRecords(coin, RecordType.DEPOSIT, status)
    }

    override fun getWithdrawalRecords(coin: CoinCode, status: RecordStatus): Single<List<Record>> {
        return assetRepository.getDepositAndWithdrawalRecords(coin, RecordType.WITHDRAW, status)
    }

    override fun getCoinBalance(coin: CoinCode) = assetRepository.getCoinBalance(coin)

    override fun getAllCoinBalances(page: Int, limit: Int): Single<List<CoinBalance>> {
        return assetRepository.getCoinBalanceByPage(page, limit)
    }

    override fun createBuyOrder(symbol: String, price: Double, amount: Double): Single<OrderId> {
        return tradingRepository.createOrder(symbol, OrderType.BUY, price, amount)
    }

    override fun createSellOrder(symbol: String, price: Double, amount: Double): Single<OrderId> {
        return tradingRepository.createOrder(symbol, OrderType.SELL, price, amount)
    }

    override fun getActiveBuyOrders(symbol: String): Single<List<Order>> {
        return tradingRepository.getActiveOrders(symbol, OrderType.BUY)
    }

    override fun getActiveSellOrders(symbol: String): Single<List<Order>> {
        return tradingRepository.getActiveOrders(symbol, OrderType.SELL)
    }

    override fun cancelBuyOrder(symbol: String, orderOid: String): Completable {
        return tradingRepository.cancelOrder(symbol, OrderType.BUY, orderOid)
    }

    override fun cancelSellOrder(symbol: String, orderOid: String): Completable {
        return tradingRepository.cancelOrder(symbol, OrderType.SELL, orderOid)
    }

    override fun cancelAllOrders(symbol: String) = tradingRepository.cancelAllOrders(symbol)

    override fun cancelAllBuyOrders(symbol: String): Completable {
        return tradingRepository.cancelAllOrders(symbol, OrderType.BUY)
    }

    override fun cancelAllSellOrders(symbol: String): Completable {
        return tradingRepository.cancelAllOrders(symbol, OrderType.SELL)
    }

    override fun getDealtOrders(type: OrderType, since: Long, before: Long, symbol: String?): Single<List<MergedDealtOrder>> {
        return tradingRepository.getMergedDealtOrders(symbol, type, since, before)
    }

    override fun getDealtOrders(symbol: String, type: OrderType): Single<List<SpecificDealtOrder>> {
        return tradingRepository.getSpecificDealtOrders(symbol, type)
    }

    override fun getOrderDetails(orderOid: String, symbol: String, type: OrderType): Single<OrderDetails> {
        return tradingRepository.getOrderDetails(orderOid, symbol, type)
    }

    override fun getTradingFavouriteSymbolsTick(market: String, symbol: String): Single<List<TradingSymbolsTick>> {
        return marketRepository.getTradingFavouriteSymbolsTick(market, symbol)
    }

    override fun getTradingStickSymbolsTick(market: String, symbol: String): Single<List<TradingSymbolsTick>> {
        return marketRepository.getTradingStickSymbolsTick(market, symbol)
    }

    override fun getStickSymbols() = marketRepository.getStickSymbols()

    override fun getFavouriteSymbols() = marketRepository.getFavouriteSymbols()

    override fun addFavouriteSymbol(symbol: String) = marketRepository.addFavouriteSymbol(symbol)

    override fun deleteFavouriteSymbol(symbol: String) = marketRepository.deleteFavouriteSymbol(symbol)

    override fun addStickSymbol(symbol: String) = marketRepository.addStickSymbol(symbol)

    override fun deleteStickSymbol(symbol: String) = marketRepository.deleteStickSymbol(symbol)
}