package com.abohomol.sdk.user

import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

interface ProfileService {

    @GET
    fun getUserProfile(@HeaderMap headers: Map<String, String>, @Url url: String): Single<ProfileResponse>

    companion object Factory {

        fun create(host: String, httpClient: OkHttpClient): ProfileService {
            val retrofit = Retrofit.Builder()
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(host)
                    .build()
            return retrofit.create(ProfileService::class.java)
        }
    }
}