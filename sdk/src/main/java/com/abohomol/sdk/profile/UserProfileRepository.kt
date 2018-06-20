package com.abohomol.sdk.profile

import io.reactivex.Single

interface UserProfileRepository {

    fun getUserProfile(): Single<UserProfile>
}