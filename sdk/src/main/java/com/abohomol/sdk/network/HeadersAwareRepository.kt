package com.abohomol.sdk.network

abstract class HeadersAwareRepository {

    protected fun getHeaders(secret: String, path: String, query: String): MutableMap<String, String> {
        val nonce = System.currentTimeMillis()
        val signature = Sha256Signature(path, query, secret, nonce)
        val headers = mutableMapOf<String, String>()
        headers[NONCE_HEADER] = nonce.toString()
        headers[SIGNATURE_HEADER] = signature.value()
        return headers
    }

    companion object {
        private const val NONCE_HEADER = "KC-API-NONCE"
        private const val SIGNATURE_HEADER = "KC-API-SIGNATURE"
    }

}