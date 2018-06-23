package com.abohomol.kukot.user

import com.abohomol.kukot.user.model.UserProfile
import io.reactivex.Single

interface UserProfileRepository {

    fun getUserProfile(): Single<UserProfile>
}