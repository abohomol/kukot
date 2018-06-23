package com.abohomol.sdk

import com.abohomol.sdk.asset.RetrofitAssetRepository
import com.abohomol.sdk.asset.AssetService
import com.abohomol.sdk.currency.RetrofitCurrencyRepository
import com.abohomol.sdk.currency.CurrencyService
import com.abohomol.sdk.language.RetrofitLanguageRepository
import com.abohomol.sdk.language.LanguageService
import com.abohomol.sdk.network.DefaultHeaderAttachInterceptor
import com.abohomol.sdk.network.RestServiceBuilder
import com.abohomol.sdk.trading.RetrofitTradingRepository
import com.abohomol.sdk.trading.TradingService
import com.abohomol.sdk.user.ProfileService
import com.abohomol.sdk.user.RetrofitUserProfileRepository
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class KuCoin {

    companion object {

        @JvmStatic
        fun create(key: String, secret: String, host: String = PRODUCTION_HOST): KuCoinService {
            val headerInterceptor = DefaultHeaderAttachInterceptor(key)
            val interceptors = mutableListOf<Interceptor>(headerInterceptor)
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                interceptors.add(logging)
            }
            val serviceBuilder = RestServiceBuilder(host, interceptors)

            val profileService = serviceBuilder.build(ProfileService::class.java)
            val userProfileRepository = RetrofitUserProfileRepository(profileService, secret)

            val languageService = serviceBuilder.build(LanguageService::class.java)
            val languageRepository = RetrofitLanguageRepository(languageService, secret)

            val currencyService = serviceBuilder.build(CurrencyService::class.java)
            val currencyRepository = RetrofitCurrencyRepository(currencyService, secret)

            val assetService = serviceBuilder.build(AssetService::class.java)
            val assetRepository = RetrofitAssetRepository(assetService, secret)

            val tradingService = serviceBuilder.build(TradingService::class.java)
            val tradingRepository = RetrofitTradingRepository(tradingService, secret)

            return DefaultKuCoinService(userProfileRepository,
                    languageRepository,
                    currencyRepository,
                    assetRepository,
                    tradingRepository)
        }

    }

}