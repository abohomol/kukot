package com.abohomol.sdk

import com.abohomol.sdk.profile.UserProfile
import com.abohomol.sdk.profile.UserProfileRepository
import io.reactivex.Single

class DefaultKuCoinService(
        private val userProfileRepository: UserProfileRepository
) : KuCoinService {

    override fun getUserProfile(): Single<UserProfile> = userProfileRepository.getUserProfile()
}