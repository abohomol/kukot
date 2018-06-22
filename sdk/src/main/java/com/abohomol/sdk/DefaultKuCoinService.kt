package com.abohomol.sdk

import com.abohomol.sdk.currency.CurrencyRepository
import com.abohomol.sdk.currency.model.CoinCode
import com.abohomol.sdk.currency.model.CurrencyCode
import com.abohomol.sdk.language.LanguageRepository
import com.abohomol.sdk.user.UserProfileRepository

class DefaultKuCoinService(
        private val userProfileRepository: UserProfileRepository,
        private val languageRepository: LanguageRepository,
        private val currencyRepository: CurrencyRepository
) : KuCoinService {

    override fun getUserProfile() = userProfileRepository.getUserProfile()

    override fun getLanguages() = languageRepository.getLanguages()

    override fun changeLanguage(language: String) = languageRepository.changeLanguage(language)

    override fun getCurrencies() = currencyRepository.getCurrencies()

    override fun changeDefaultCurrency(currency: CurrencyCode) = currencyRepository.changeDefaultCurrency(currency)

    override fun getExchangeRates(vararg coins: CoinCode) = currencyRepository.getExchangeRates(coins.asList())
}