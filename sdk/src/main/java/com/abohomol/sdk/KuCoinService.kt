package com.abohomol.sdk

import com.abohomol.sdk.profile.UserProfile
import io.reactivex.Single

interface KuCoinService {

    fun getUserProfile(): Single<UserProfile>


}