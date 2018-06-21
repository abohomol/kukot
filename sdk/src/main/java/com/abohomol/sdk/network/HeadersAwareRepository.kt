package com.abohomol.sdk.network

abstract class HeadersAwareRepository(private val secret: String) {

    protected fun getHeaders(query: String): MutableMap<String, String> {
        val nonce = System.currentTimeMillis()
        val signature = Sha256Signature(endpoint(), query, secret, nonce)
        val headers = mutableMapOf<String, String>()
        headers[NONCE_HEADER] = nonce.toString()
        headers[SIGNATURE_HEADER] = signature.value()
        return headers
    }

    abstract fun endpoint(): String

    companion object {
        private const val NONCE_HEADER = "KC-API-NONCE"
        private const val SIGNATURE_HEADER = "KC-API-SIGNATURE"
    }

}