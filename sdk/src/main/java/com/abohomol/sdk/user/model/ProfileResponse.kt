package com.abohomol.sdk.user.model

import com.abohomol.sdk.network.BaseResponse
import com.google.gson.annotations.SerializedName

data class ProfileResponse(val data: UserProfile) : BaseResponse()

data class UserProfile(
        val oid: String,
        val name: String,
        val nickname: String,
        val currency: String,
        val language: String,
        val phone: String,
        val email: String,
        val credentialNumber: String,
        val baseFeeRate: String,
        @SerializedName("referrer_code")
        val referrerCode: String,
        val photoCredentialValidated: Boolean,
        val videoValidated: Boolean,
        val hasCredential: Boolean,
        val phoneValidated: Boolean,
        val credentialValidated: Boolean,
        val googleTwoFaBinding: Boolean,
        val hasTradePassword: Boolean,
        val emailValidated: Boolean,
        val loginRecord: Map<String, LoginRecord>
)

data class LoginRecord(val ip: String, val context: String, val time: Long)