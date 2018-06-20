package com.abohomol.sdk.user

import com.abohomol.sdk.network.HeadersAwareRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserProfileRetrofitRepository(
        private val profileService: ProfileService,
        private val secret: String
) : HeadersAwareRepository(), UserProfileRepository {

    override fun getUserProfile(): Single<UserProfile> {
        val headers = getHeaders(secret, ENDPOINT, "")
        return profileService.getUserProfile(headers, ENDPOINT)
                .map {
                    it.profile
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private const val ENDPOINT = "/v1/user/info"
    }
}