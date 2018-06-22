package com.abohomol.sdk.language.model

import com.abohomol.sdk.network.BaseResponse
import com.abohomol.sdk.network.LanguageCode

data class LanguagesResponse(private val data: List<List<Any>>) : BaseResponse() {

    fun languages(): List<UserLanguage> {
        return data.map { UserLanguage(it[0] as String, it[1] as String, it[2] as Boolean) }
    }
}

data class UserLanguage(val code: LanguageCode, val name: String, val available: Boolean)

