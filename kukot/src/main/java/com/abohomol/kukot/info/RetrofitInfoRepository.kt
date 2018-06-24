package com.abohomol.kukot.info

import com.abohomol.kukot.KuCoinInfoService
import com.abohomol.kukot.info.model.CoinInfo
import com.abohomol.kukot.info.model.MarketTick
import com.abohomol.kukot.network.BaseRepository
import com.abohomol.kukot.network.CoinCode
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitInfoRepository(
        private val infoService: InfoService
) : BaseRepository(""), KuCoinInfoService {

    override fun getMarketTicks(): Single<List<MarketTick>> {
        return infoService.getMarketTicks()
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTradingMarkets(): Single<List<String>> {
        return infoService.getTradingMarkets()
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCoinInfo(coin: CoinCode): Single<CoinInfo> {
        return infoService.getCoinInfo(coin)
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCoinsInfo(): Single<List<CoinInfo>> {
        return infoService.getCoinsInfo()
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}