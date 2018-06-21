package com.abohomol.sdk.language

import com.abohomol.sdk.language.model.UserLanguage
import com.abohomol.sdk.network.BaseResponse
import com.abohomol.sdk.network.HeadersAwareRepository
import com.abohomol.sdk.network.NotSuccessfulRequestException
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LanguageRetrofitRepository(
        private val languageService: LanguageService,
        secret: String
) : HeadersAwareRepository(secret), LanguageRepository {

    override fun getLanguages(): Single<List<UserLanguage>> {
        return languageService.getLanguages()
                .doOnSuccess { onSuccess(it) }
                .map { it.languages() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun changeLanguage(languageCode: String): Completable {
        val headers = getHeaders("lang=$languageCode")
        return languageService.changeLanguage(headers, endpoint(), languageCode)
                .doOnSuccess { onSuccess(it) }
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun onSuccess(response: BaseResponse) {
        if (!response.success) {
            throw NotSuccessfulRequestException(response)
        }
    }

    override fun endpoint() = "/v1/user/change-lang"
}