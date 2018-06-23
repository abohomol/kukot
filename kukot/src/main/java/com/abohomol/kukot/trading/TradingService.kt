package com.abohomol.kukot.trading

import com.abohomol.kukot.network.BaseResponse
import com.abohomol.kukot.trading.model.ActiveOrdersResponse
import com.abohomol.kukot.trading.model.CreateOrderResponse
import com.abohomol.kukot.trading.model.MergedDealtOrdersResponse
import com.abohomol.kukot.trading.model.OrderDetailsResponse
import com.abohomol.kukot.trading.model.SpecificDealtOrdersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface TradingService {

    @POST
    fun createOrder(@HeaderMap
                    headers: Map<String, String>,
                    @Url
                    url: String,
                    @QueryMap
                    queries: Map<String, String>): Single<CreateOrderResponse>

    @GET
    fun getActiveOrders(@HeaderMap
                        headers: Map<String, String>,
                        @Url
                        url: String,
                        @QueryMap
                        queries: Map<String, String>): Single<ActiveOrdersResponse>

    @POST
    fun cancelOrder(@HeaderMap
                    headers: Map<String, String>,
                    @Url
                    url: String,
                    @QueryMap
                    queries: Map<String, String>): Single<BaseResponse>

    @POST
    fun cancelAllOrders(@HeaderMap
                        headers: Map<String, String>,
                        @Url
                        url: String,
                        @QueryMap
                        queries: Map<String, String>): Single<BaseResponse>

    @GET
    fun getMergedDealtOrders(@HeaderMap
                             headers: Map<String, String>,
                             @Url
                             url: String,
                             @QueryMap
                             queries: Map<String, String>): Single<MergedDealtOrdersResponse>

    @GET
    fun getSpecificDealtOrders(@HeaderMap
                               headers: Map<String, String>,
                               @Url
                               url: String,
                               @QueryMap
                               queries: Map<String, String>): Single<SpecificDealtOrdersResponse>

    @GET
    fun getOrderDetails(@HeaderMap
                        headers: Map<String, String>,
                        @Url
                        url: String,
                        @QueryMap
                        queries: Map<String, String>): Single<OrderDetailsResponse>
}