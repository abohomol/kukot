package com.abohomol.kukot.asset

import com.abohomol.kukot.asset.model.CoinBalance
import com.abohomol.kukot.asset.model.DepositAddress
import com.abohomol.kukot.asset.model.Record
import com.abohomol.kukot.asset.model.RecordStatus
import com.abohomol.kukot.asset.model.RecordType
import com.abohomol.kukot.network.CoinCode
import io.reactivex.Completable
import io.reactivex.Single

interface AssetRepository {

    fun getDepositAddress(coin: CoinCode): Single<DepositAddress>

    fun withdrawalApply(coin: CoinCode, amount: Double, withdrawalAddress: String): Completable

    fun cancelWithdrawal(coin: CoinCode, transactionId: String): Completable

    fun getDepositAndWithdrawalRecords(coin: CoinCode, type: RecordType, status: RecordStatus, page: Int = 0): Single<List<Record>>

    fun getCoinBalance(coin: CoinCode): Single<CoinBalance>

    fun getCoinBalanceByPage(coin: CoinCode, page: Int, limit: Int): Single<List<CoinBalance>>
}