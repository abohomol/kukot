package com.abohomol.sdk

import com.abohomol.sdk.network.DefaultHeaderAttachInterceptor
import com.abohomol.sdk.user.ProfileService
import com.abohomol.sdk.user.UserProfileRetrofitRepository
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor

class KuCoin {

    companion object {
        fun create(key: String, secret: String): KuCoinService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(DefaultHeaderAttachInterceptor(key))
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