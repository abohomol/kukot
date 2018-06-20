package com.abohomol.sdk.profile

data class UserProfile(val data: Data) {

    data class Data(val hello: String)

    /**
    {
    "data": {
    "referrer_code": "jkLmne",
    "photoCredentialValidated": true,
    "videoValidated": false,
    "language": "en_US",
    "currency": "USD",
    "oid": "59663b126732d50be3ac8bcb",
    "baseFeeRate": 1,
    "hasCredential": true,
    "credentialNumber": "5103**********0013",
    "phoneValidated": true,
    "phone": "18******139",
    "credentialValidated": true,
    "googleTwoFaBinding": true,
    "nickname": null,
    "name": "*醇",
    "hasTradePassword": true,
    "emailValidated": true,
    "email": "robert2041@163.com",
    "loginRecord": {
    "last": {
    "ip": "171.212.112.61",
    "context": null,
    "time": 1509942507000
    },
    "current": {
    "ip": "127.0.0.1",
    "context": null,
    "time": 1509942560000
    }
    }
    }
    }
     */
}