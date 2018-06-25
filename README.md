![Logo](logo/logo_small.png)
============

Kukot is Android Kotlin SDK for [KuCoin](https://www.kucoin.com/) crypto-currency exchange.

[![Build Status](https://travis-ci.org/abohomol/kukot.svg?branch=master)](https://travis-ci.org/abohomol/kukot) [![](https://jitpack.io/v/abohomol/kukot.svg)](https://jitpack.io/#abohomol/kukot) [![Apache 2.0 License](https://img.shields.io/hexpm/l/plug.svg) ](https://github.com/abohomol/kukot/blob/master/LICENSE)


## Usage

### Basics
SDK API has two separate services, one of which provides an access via API KEY and Secret and the second one is open.

Restricted API service is represented by `KuCoinService` interface. It provides a neat access to the certain API groups:
 * User profile
 * Language
 * Currency
 * Assets
 * Trading
 * Market

The instance of `KuCoinService` could be obtained in the following way:

    val apiKey = "<your-api-key>"
    val secret = "<your-secret>"
    val service = KuCoin.create(apiKey, secret)

Open API service is represented by `KuCoinInfoService` interface and provides and access to a basic exchange information like market ticks, trading markets, coin information, etc.

The instance of `KuCoinService` could be obtained in the following way:

    val infoService = KuCoin.createInfoService()

### Examples

Below are few examples of SDK usage which can give a general idea of API capabilities. For more information and detailed documentation see `KuCoinService` and `KuCoinInfoService` interfaces.

#### Get user profile

    service.getUserProfile()
            .subscribe { profile -> Log.d("Kukot", "Profile for user with email ${profile.email} retrieved!") }

#### Change language

    service.changeLanguage("en_US")
            .subscribe { Log.d("Kukot", "Language changed!") }

#### Change currency

    service.changeCurrency("EUR")
            .subscribe { Log.d("Kukot", "Currency changed!") }

#### Get deposit address

    service.getCoinDepositAddress("KCS")
            .subscribe { depositAddress -> Log.d("Kukot", "Deposit address: ${depositAddress.address}") }

#### Withdraw coin

    service.withdrawCoin("KCS", 100.0, address)
            .subscribe { Log.d("Kukot", "Withdraw request created!") }

#### Get coin balance

    service.getCoinBalance("KCS")
            .subscribe { coinBalance -> Log.d("Kukot", "You have ${coinBalance.balance} KCS") }

#### Create buy order

    val price = 3.5
    val amount = 100.0
    service.createBuyOrder("KCS-BTC", price, amount)
            .subscribe { orderId -> Log.d("Kukot", "Buy order with id $orderId created!") }

#### Get list of active buy orders

    service.getActiveBuyOrders("KCS-BTC")
            .subscribe { orders -> Log.d("Kukot", "Found ${orders.size} active buy orders") }

### Multiple hosts support

By default Kukot uses production backend environment under the hood but since testing and debugging against production host might not always be a good idea, Kukot provides a way to specify host manually or use one of constants defined in `EnvironmentConstants` file and visible in the global namespace:

    val apiKey = "<your-api-key>"
    val secret = "<your-secret>"
    val host = DEBUG_PROXY_HOST
    val service = KuCoin.create(apiKey, secret, host)

Same works for info service:

    val host = DEBUG_PROXY_HOST
    val infoService = KuCoin.createInfoService(host)

### Threading

Since Kukot uses [RxJava2](https://github.com/ReactiveX/RxJava) as a part of its API, all network calls are scheduled automatically in the background thread but user receives results in the Android main thread, which is safe for UI:

    val service = KuCoin.create(apiKey, secret)
    service.getUserProfile() // background thread
                .subscribe({
                    textView.text = it.email // main thread
                }, {
                    Log.e(TAG, "Unable to acquire user profile", it) // main thread
                })

### Testing

Kukot is heavily relying on interfaces so you can easily test it by mocking the whole `KuCoinService` or `KuCoinInfoService` facades with Mockito or implementing interfaces on your own and passing them around your application code without any hassle.

## Sample application

There is a [sample application](/sample) for Android in the current repository which can give a basic idea of how to use SDK. By clicking on the button it simply requests user profile from the KuCoin exchange, retrieves email address and shows it as a Toast message. In order for sample application to work you need to replace API Key and Secret in the MainActivity.kt.

## Download

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.abohomol:kukot:1.0.0'
	}

## License

    Copyright 2018 Anton Bohomol

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.