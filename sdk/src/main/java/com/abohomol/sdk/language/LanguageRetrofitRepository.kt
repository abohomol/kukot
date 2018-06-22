package com.abohomol.sdk.language

import com.abohomol.sdk.language.model.UserLanguage
import com.abohomol.sdk.network.BaseRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LanguageRetrofitRepository(
        private val languageService: LanguageService,
        secret: String
) : BaseRepository(secret), LanguageRepository {

    override fun getLanguages(): Single<List<UserLanguage>> {
        return languageService.getLanguages()
                .doOnSuccess { onResponse(it) }
                .map { it.languages() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun changeLanguage(languageCode: String): Completable {
        val headers = getHeaders(ENDPOINT, "lang=$languageCode")
        return languageService.changeLanguage(headers, ENDPOINT, languageCode)
                .doOnSuccess { onResponse(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private const val ENDPOINT = "/v1/user/change-lang"
    }
}