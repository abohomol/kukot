package com.abohomol.sdk.user

import io.reactivex.Single

interface UserProfileRepository {

    fun getUserProfile(): Single<UserProfile>
}