package com.abohomol.kukot.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Request interceptor used to attach default headers like API KEY.
 */
class DefaultHeaderAttachInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader(CONTENT_TYPE_HEADER, "application/json")
        builder.addHeader(API_KEY_HEADER, apiKey)
        return chain.proceed(builder.build())
    }

    companion object {
        private const val CONTENT_TYPE_HEADER = "Content-Type"
        private const val API_KEY_HEADER = "KC-API-KEY"
    }
}