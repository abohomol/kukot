package com.abohomol.sdk

import com.abohomol.sdk.language.LanguageRetrofitRepository
import com.abohomol.sdk.language.LanguageService
import com.abohomol.sdk.network.DefaultHeaderAttachInterceptor
import com.abohomol.sdk.network.RestServiceBuilder
import com.abohomol.sdk.user.ProfileService
import com.abohomol.sdk.user.UserProfileRetrofitRepository
import okhttp3.logging.HttpLoggingInterceptor

class KuCoin {

    companion object {

        fun create(key: String, secret: String): KuCoinService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val headerInterceptor = DefaultHeaderAttachInterceptor(key)
            val host = "https://api.kucoin.com"
            val serviceBuilder = RestServiceBuilder(host, mutableListOf(logging, headerInterceptor))
            val profileService = serviceBuilder.build(ProfileService::class.java)
            val userProfileRepository = UserProfileRetrofitRepository(profileService, secret)
            val languageService = serviceBuilder.build(LanguageService::class.java)
            val languageRepository = LanguageRetrofitRepository(languageService, secret)
            return DefaultKuCoinService(userProfileRepository, languageRepository)
        }
    }

}