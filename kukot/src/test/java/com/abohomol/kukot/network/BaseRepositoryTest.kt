package com.abohomol.kukot.network

import org.junit.Assert
import org.junit.Test

class BaseRepositoryTest {

    @Test
    fun shouldReturnTwoHeaders() {
        val tested = object : BaseRepository("secret") {
            fun getHeaders() = getHeaders("endpoint", "query")
        }
        val headers = tested.getHeaders()
        Assert.assertEquals(2, headers.size)
    }

    @Test
    fun shouldContainNonceHeader() {
        val tested = object : BaseRepository("secret") {
            fun getHeaders() = getHeaders("endpoint", "query")
        }
        val headers = tested.getHeaders()
        Assert.assertTrue(headers.containsKey("KC-API-NONCE"))
    }

    @Test
    fun shouldContainApiKeyHeader() {
        val tested = object : BaseRepository("secret") {
            fun getHeaders() = getHeaders("endpoint", "query")
        }
        val headers = tested.getHeaders()
        Assert.assertTrue(headers.containsKey("KC-API-SIGNATURE"))
    }

    @Test(expected = KuCoinRequestError::class)
    fun shouldThrowExceptionOnUnsuccessfulResponse() {
        val tested = object : BaseRepository("secret") {
            fun onResponseProxy(response: BaseResponse) = onResponse(response)
        }
        tested.onResponseProxy(BaseResponse().apply { success = false })
    }
}