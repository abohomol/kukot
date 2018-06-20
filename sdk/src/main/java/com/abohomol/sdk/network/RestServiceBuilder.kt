package com.abohomol.sdk.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class RestServiceBuilder(
        private val context: Context,
        private val interceptor: Interceptor
) {

    fun build() {
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .build()

    }

    companion object {
        private const val TIMEOUT_MS = 2500L
    }
}