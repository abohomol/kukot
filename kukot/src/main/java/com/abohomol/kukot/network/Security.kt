package com.abohomol.kukot.network

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.binary.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


interface Signature {

    fun value(): String
}

class Sha256Signature(
        private val path: String,
        private val query: String,
        private val secret: String,
        private val nonce: Long
) : Signature {

    override fun value(): String {
        val toSign = "$path/$nonce/$query"
        val signatureStr = String(Base64.encodeBase64(toSign.toByteArray(CHARSET)))
        val sha256Hmac = Mac.getInstance(ALGORITHM)
        val secretKeySpec = SecretKeySpec(secret.toByteArray(CHARSET), ALGORITHM)
        sha256Hmac.init(secretKeySpec)
        val encrypted = sha256Hmac.doFinal(signatureStr.toByteArray(CHARSET))
        return String(Hex.encodeHex(encrypted))
    }

    companion object {
        private const val ALGORITHM = "HmacSHA256"
        private val CHARSET = charset("UTF-8")
    }
}