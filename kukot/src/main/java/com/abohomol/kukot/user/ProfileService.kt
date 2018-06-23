package com.abohomol.kukot.user

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

interface ProfileService {

    @GET
    fun getUserProfile(@HeaderMap headers: Map<String, String>, @Url url: String): Single<ProfileResponse>

}