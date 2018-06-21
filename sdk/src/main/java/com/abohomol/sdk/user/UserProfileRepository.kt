package com.abohomol.sdk.user

import com.abohomol.sdk.user.model.UserProfile
import io.reactivex.Single

interface UserProfileRepository {

    fun getUserProfile(): Single<UserProfile>
}