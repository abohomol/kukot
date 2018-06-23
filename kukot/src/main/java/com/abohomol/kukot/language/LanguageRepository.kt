package com.abohomol.kukot.language

import com.abohomol.kukot.language.model.UserLanguage
import io.reactivex.Completable
import io.reactivex.Single

interface LanguageRepository {

    fun getLanguages(): Single<List<UserLanguage>>

    fun changeLanguage(languageCode: String): Completable
}