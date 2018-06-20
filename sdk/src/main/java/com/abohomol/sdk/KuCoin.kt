package com.abohomol.sdk

import com.abohomol.sdk.network.ApiKeyHeaderAttachInterceptor
import com.abohomol.sdk.profile.ProfileService
import com.abohomol.sdk.profile.UserProfileRetrofitRepository
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class KuCoin {

    companion object {
        fun create(key: String, secret: String): KuCoinService {
            val client = OkHttpClient.Builder()
                    .addInterceptor(ApiKeyHeaderAttachInterceptor(key))
                    .readTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
                    .writeTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
                    .build()
            val host = "https://api.kucoin.com"
            val profileService = ProfileService.create(host, client)
            val userProfileRepository = UserProfileRetrofitRepository(profileService, secret)
            return DefaultKuCoinService(userProfileRepository)
        }

        private const val TIMEOUT_MS = 2500L
    }

}