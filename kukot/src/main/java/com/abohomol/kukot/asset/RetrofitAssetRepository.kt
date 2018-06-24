package com.abohomol.kukot.asset

import com.abohomol.kukot.asset.model.CoinBalance
import com.abohomol.kukot.asset.model.DepositAddress
import com.abohomol.kukot.asset.model.Record
import com.abohomol.kukot.asset.model.RecordStatus
import com.abohomol.kukot.asset.model.RecordType
import com.abohomol.kukot.network.BaseRepository
import com.abohomol.kukot.network.CoinCode
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitAssetRepository(
        private val assetService: AssetService,
        secret: String
) : BaseRepository(secret) {

    fun getDepositAddress(coin: CoinCode): Single<DepositAddress> {
        val endpoint = "/v1/account/$coin/wallet/address"
        val headers = getHeaders(endpoint, "")
        return assetService.getDepositAddress(headers, endpoint)
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun withdraw(coin: CoinCode,
                 amount: Double,
                 withdrawalAddress: String): Completable {
        val endpoint = "/v1/account/$coin/withdraw/apply"
        val query = "address=$withdrawalAddress&amount=$amount&coin=$coin"
        val headers = getHeaders(endpoint, query)
        val queries = mapOf(
                "address" to withdrawalAddress,
                "amount" to amount.toString(),
                "coin" to coin
        )
        return assetService.createWithdrawalApply(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun cancelWithdrawal(coin: CoinCode, transactionId: String): Completable {
        val endpoint = "/v1/account/$coin/withdraw/cancel"
        val headers = getHeaders(endpoint, "txOid=$transactionId")
        return assetService.cancelWithdrawal(headers, endpoint, transactionId)
                .doOnSuccess { onResponse(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDepositAndWithdrawalRecords(coin: CoinCode,
                                       type: RecordType,
                                       status: RecordStatus,
                                       page: Int): Single<List<Record>> {
        val endpoint = "/v1/account/$coin/wallet/records"
        val query = "page=$page&status=${status.name}&type=${type.name}"
        val headers = getHeaders(endpoint, query)
        val queries = mapOf(
                "page" to page.toString(),
                "status" to status.name,
                "type" to type.name
        )
        return assetService.getDepositAndWithdrawalRecords(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .map { it.data.datas }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCoinBalance(coin: CoinCode): Single<CoinBalance> {
        val endpoint = "/v1/account/$coin/balance"
        val headers = getHeaders(endpoint, "")
        return assetService.getCoinBalance(headers, endpoint)
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCoinBalanceByPage(page: Int, limit: Int): Single<List<CoinBalance>> {
        val endpoint = "/v1/account/balances"
        val headers = getHeaders(endpoint, "limit=$limit&page=$page")
        val queries = mapOf(
                "limit" to limit.toString(),
                "page" to page.toString()
        )
        return assetService.getCoinsBalanceByPage(headers, endpoint, queries)
                .doOnSuccess { onResponse(it) }
                .map { it.data.datas }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}