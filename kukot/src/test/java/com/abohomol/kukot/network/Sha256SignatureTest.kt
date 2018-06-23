package com.abohomol.kukot.network

import org.junit.Assert.assertEquals
import org.junit.Test

class Sha256SignatureTest {

    @Test
    fun shouldProduceCorrectSignatureValue() {
        val path = "/v1/user/info"
        val query = ""
        val secret = "43a45hgt-73l5-g33h-ht44-f35h5j668h21"
        val nonce = 1529571645891L
        val signature = Sha256Signature(path, query, secret, nonce)
        val expected = "80b6b4366c2c8c9cae6fb602ac233dc1289e08b9cf9a216a7a6f9a591ab7865f"
        assertEquals(expected, signature.value())
    }
}