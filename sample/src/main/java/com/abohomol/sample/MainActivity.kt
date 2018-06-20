package com.abohomol.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abohomol.sdk.KuCoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = KuCoin.create(API_KEY, SECRET)
        service.getUserProfile()
                .subscribe({
                    Log.e("KUCOIN", "Success $it")
                           }, {
                    Log.e("KUCOIN", "Failure")
                })
    }

    companion object {
        val API_KEY = ""
        val SECRET = ""
    }
}
