package com.abohomol.sdk.user

import com.abohomol.sdk.network.BaseRepository
import com.abohomol.sdk.user.model.UserProfile
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserProfileRetrofitRepository(
        private val profileService: ProfileService,
        secret: String
) : BaseRepository(secret), UserProfileRepository {

    override fun getUserProfile(): Single<UserProfile> {
        return profileService.getUserProfile(getHeaders(""), endpoint())
                .doOnSuccess { onResponse(it) }
                .map { it.data }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun endpoint() = "/v1/user/info"
}