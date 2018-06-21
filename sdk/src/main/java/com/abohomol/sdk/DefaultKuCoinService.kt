package com.abohomol.sdk

import com.abohomol.sdk.language.LanguageRetrofitRepository
import com.abohomol.sdk.user.UserProfileRepository

class DefaultKuCoinService(
        private val userProfileRepository: UserProfileRepository,
        private val languageRepository: LanguageRetrofitRepository
) : KuCoinService {

    override fun getUserProfile() = userProfileRepository.getUserProfile()

    override fun getLanguages() = languageRepository.getLanguages()

    override fun changeLanguage(language: String) = languageRepository.changeLanguage(language)
}