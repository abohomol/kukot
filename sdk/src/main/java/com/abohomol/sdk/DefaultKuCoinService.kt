package com.abohomol.sdk

import com.abohomol.sdk.user.UserProfile
import com.abohomol.sdk.user.UserProfileRepository
import io.reactivex.Single

class DefaultKuCoinService(
        private val userProfileRepository: UserProfileRepository
) : KuCoinService {

    override fun getUserProfile(): Single<UserProfile> = userProfileRepository.getUserProfile()
}