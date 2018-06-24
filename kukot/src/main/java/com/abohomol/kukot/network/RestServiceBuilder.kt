package com.abohomol.kukot.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestServiceBuilder(host: String, interceptors: List<Interceptor> = listOf()) {

    private val retrofit: Retrofit

    init {
        val clientBuilder = OkHttpClient.Builder()
        interceptors.forEach { clientBuilder.addInterceptor(it) }
        clientBuilder.readTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
        clientBuilder.writeTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)

        retrofit = Retrofit.Builder()
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(host)
                .build()

    }

    fun <T> build(service: Class<T>): T = retrofit.create(service)

    companion object {
        private const val TIMEOUT_MS = 10000L
    }
}