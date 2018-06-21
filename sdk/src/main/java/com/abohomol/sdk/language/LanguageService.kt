package com.abohomol.sdk.language

import com.abohomol.sdk.language.model.LanguagesResponse
import com.abohomol.sdk.network.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface LanguageService {

    @GET("/v1/open/lang-list")
    fun getLanguages(): Single<LanguagesResponse>

    @POST
    fun changeLanguage(@HeaderMap
                       headers: Map<String, String>,
                       @Url
                       url: String,
                       @Query("lang")
                       language: String): Single<BaseResponse>
}