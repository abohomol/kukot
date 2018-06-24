package com.abohomol.kukot.network

abstract class BaseRepository(private val secret: String) {

    protected fun getHeaders(endpoint: String, query: String): MutableMap<String, String> {
        val nonce = System.currentTimeMillis()
        val signature = Sha256Signature(endpoint, query, secret, nonce)
        val headers = mutableMapOf<String, String>()
        headers[NONCE_HEADER] = nonce.toString()
        headers[SIGNATURE_HEADER] = signature.value()
        return headers
    }

    protected fun onResponse(response: BaseResponse) {
        if (!response.success) {
            throw KuCoinRequestError(response)
        }
    }

    companion object {
        private const val NONCE_HEADER = "KC-API-NONCE"
        private const val SIGNATURE_HEADER = "KC-API-SIGNATURE"
    }

}