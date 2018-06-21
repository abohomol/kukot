package com.abohomol.sdk

import com.abohomol.sdk.language.model.UserLanguage
import com.abohomol.sdk.user.model.UserProfile
import io.reactivex.Completable
import io.reactivex.Single

interface KuCoinService {

    fun getUserProfile(): Single<UserProfile>

    fun getLanguages(): Single<List<UserLanguage>>

    fun changeLanguage(languageCode: String): Completable
}