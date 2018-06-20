package com.abohomol.sdk.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Request interceptor used to attach API KEY header.
 */
class ApiKeyHeaderAttachInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader(API_KEY_HEADER, apiKey)
        return chain.proceed(builder.build())
    }

    companion object {
        private const val API_KEY_HEADER = "KC-API-KEY"
    }
}