package com.abohomol.kukot.user

import com.abohomol.kukot.network.BaseRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitUserProfileRepository(
        private val profileService: ProfileService,
        secret: String
) : BaseRepository(secret) {

    fun getUserProfile(): Single<UserProfile> {
        return profileService.getUserProfile(getHeaders(ENDPOINT, ""), ENDPOINT)
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private const val ENDPOINT = "/v1/user/info"
    }
}