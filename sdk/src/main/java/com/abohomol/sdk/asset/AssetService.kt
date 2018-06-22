package com.abohomol.sdk.asset

import com.abohomol.sdk.asset.model.CoinBalancePageResponse
import com.abohomol.sdk.asset.model.CoinBalanceResponse
import com.abohomol.sdk.asset.model.DepositAddressResponse
import com.abohomol.sdk.asset.model.DepositAndWithdrawalRecordsResponse
import com.abohomol.sdk.network.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface AssetService {

    @GET
    fun getDepositAddress(@HeaderMap
                          headers: Map<String, String>,
                          @Url
                          url: String): Single<DepositAddressResponse>


    @POST
    fun createWithdrawalApply(@HeaderMap
                              headers: Map<String, String>,
                              @Url
                              url: String,
                              @QueryMap
                              queries: Map<String, String>): Single<BaseResponse>

    @POST
    fun cancelWithdrawal(@HeaderMap
                         headers: Map<String, String>,
                         @Url
                         url: String,
                         @Query("txOid")
                         transactionId: String): Single<BaseResponse>

    @GET
    fun getDepositAndWithdrawalRecords(@HeaderMap
                                      headers: Map<String, String>,
                                       @Url
                                      url: String,
                                       @QueryMap
                                      queries: Map<String, String>): Single<DepositAndWithdrawalRecordsResponse>

    @GET
    fun getCoinBalance(@HeaderMap
                       headers: Map<String, String>,
                       @Url
                       url: String): Single<CoinBalanceResponse>

    @GET
    fun getCoinsBalanceByPage(@HeaderMap
                              headers: Map<String, String>,
                              @Url
                              url: String,
                              @QueryMap
                              queries: Map<String, String>): Single<CoinBalancePageResponse>
}