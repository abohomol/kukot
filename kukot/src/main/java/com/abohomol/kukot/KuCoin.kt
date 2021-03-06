package com.abohomol.kukot

import com.abohomol.kukot.asset.RetrofitAssetRepository
import com.abohomol.kukot.asset.AssetService
import com.abohomol.kukot.currency.RetrofitCurrencyRepository
import com.abohomol.kukot.currency.CurrencyService
import com.abohomol.kukot.info.InfoService
import com.abohomol.kukot.info.RetrofitInfoRepository
import com.abohomol.kukot.language.RetrofitLanguageRepository
import com.abohomol.kukot.language.LanguageService
import com.abohomol.kukot.market.MarketService
import com.abohomol.kukot.market.RetrofitMarketRepository
import com.abohomol.kukot.network.DefaultHeaderAttachInterceptor
import com.abohomol.kukot.network.RestServiceBuilder
import com.abohomol.kukot.trading.RetrofitTradingRepository
import com.abohomol.kukot.trading.TradingService
import com.abohomol.kukot.user.ProfileService
import com.abohomol.kukot.user.RetrofitUserProfileRepository
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

            val marketService = serviceBuilder.build(MarketService::class.java)
            val marketRepository = RetrofitMarketRepository(marketService, secret)

            return DefaultKuCoinService(userProfileRepository,
                    languageRepository,
                    currencyRepository,
                    assetRepository,
                    tradingRepository,
                    marketRepository)
        }

        @JvmStatic
        fun createInfoService(host: String = PRODUCTION_HOST): KuCoinInfoService {
            val serviceBuilder = RestServiceBuilder(host)
            val service = serviceBuilder.build(InfoService::class.java)
            return RetrofitInfoRepository(service)
        }

    }

}