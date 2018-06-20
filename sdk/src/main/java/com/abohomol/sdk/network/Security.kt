package com.abohomol.sdk.network

import android.util.Base64
import org.apache.commons.codec.binary.Hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


interface Signature {

    fun value(): String
}

class Sha256Signature(
        private val endpoint: String,
        private val query: String,
        private val nonce: Long,
        private val secret: String
) : Signature {

    override fun value(): String {
        val toSign = "$endpoint/$nonce/$query"

        val signatureStr = Base64.encodeToString(toSign.toByteArray(CHARSET), Base64.DEFAULT)

        val sha256Hmac = Mac.getInstance(ALGORITHM)
        val secretKeySpec = SecretKeySpec(secret.toByteArray(CHARSET), ALGORITHM)
        sha256Hmac.init(secretKeySpec)

        return Hex.encodeHexString(sha256Hmac.doFinal(signatureStr.toByteArray(CHARSET)))
    }

    companion object {
        private const val ALGORITHM = "HmacSHA256"
        private val CHARSET = charset("UTF-8")
    }
}