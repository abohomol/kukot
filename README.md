![Logo](logo/logo_small.png)
============

Kukot is Android Kotlin SDK for [KuCoin](https://www.kucoin.com/) cryptocurrency exchange.

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

### Multiple hosts support

By defult Kukot uses production backend enviroment under the hood but since testing and debugging against production host might not always be a good idea, Kukot provides a way to specify host manually or use one of constants defined in `EnvironmentConstants` file and visible in the global namespace:

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
                    helloWorld.text = it.email // main thread
                }, {
                    Log.e(TAG, "Unable to acquire user profile", it) // main thread
                })


### Testing

Kukot is heavily relying on interfaces so you can easily test it by mocking the whole `KuCoinService` or `KuCoinInfoService` facades with Mockito or implementing interfaces on your own and passing them around your application code without any hassle.

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