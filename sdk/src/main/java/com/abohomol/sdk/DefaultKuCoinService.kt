package com.abohomol.sdk

import com.abohomol.sdk.asset.AssetRepository
import com.abohomol.sdk.asset.model.CoinBalance
import com.abohomol.sdk.asset.model.Record
import com.abohomol.sdk.asset.model.RecordStatus
import com.abohomol.sdk.asset.model.RecordType
import com.abohomol.sdk.currency.CurrencyRepository
import com.abohomol.sdk.language.LanguageRepository
import com.abohomol.sdk.network.CoinCode
import com.abohomol.sdk.network.CurrencyCode
import com.abohomol.sdk.network.LanguageCode
import com.abohomol.sdk.user.UserProfileRepository
import io.reactivex.Completable
import io.reactivex.Single

class DefaultKuCoinService(
        private val userProfileRepository: UserProfileRepository,
        private val languageRepository: LanguageRepository,
        private val currencyRepository: CurrencyRepository,
        private val assetRepository: AssetRepository
) : KuCoinService {

    override fun getUserProfile() = userProfileRepository.getUserProfile()

    override fun getLanguages() = languageRepository.getLanguages()

    override fun changeLanguage(language: LanguageCode) = languageRepository.changeLanguage(language)

    override fun getCurrencies() = currencyRepository.getCurrencies()

    override fun changeDefaultCurrency(currency: CurrencyCode) = currencyRepository.changeDefaultCurrency(currency)

    override fun getExchangeRates(vararg coins: CoinCode) = currencyRepository.getExchangeRates(coins.asList())

    override fun getCoinDepositAddress(coin: CoinCode) = assetRepository.getDepositAddress(coin)

    override fun withdrawalApply(coin: CoinCode,
                                 amount: Double,
                                 withdrawalAddress: String): Completable {
        return assetRepository.withdrawalApply(coin, amount, withdrawalAddress)
    }

    override fun cancelWithdrawal(coin: CoinCode, transactionId: String): Completable {
        return assetRepository.cancelWithdrawal(coin, transactionId)
    }

    override fun getDepositAndWithdrawalRecords(coin: CoinCode,
                                                type: RecordType,
                                                status: RecordStatus,
                                                page: Int): Single<List<Record>> {
        return assetRepository.getDepositAndWithdrawalRecords(coin, type, status, page)
    }

    override fun getCoinBalance(coin: CoinCode) = assetRepository.getCoinBalance(coin)

    override fun getCoinBalanceByPage(coin: CoinCode,
                                      page: Int,
                                      limit: Int): Single<List<CoinBalance>> {
        return assetRepository.getCoinBalanceByPage(coin, page, limit)
    }
}