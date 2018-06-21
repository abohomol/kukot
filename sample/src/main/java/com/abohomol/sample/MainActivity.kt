package com.abohomol.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abohomol.sdk.KuCoin
import kotlinx.android.synthetic.main.activity_main.helloWorld

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloWorld.setOnClickListener { start() }
    }

    private fun start() {
        val service = KuCoin.create(API_KEY, SECRET)
        service.changeLanguage("ru_RU").subscribe({
                                             Log.e("KUCOIN", "Success")
                                         }, {
                                             Log.e("KUCOIN", "Failure")
                                         })
    }

    companion object {
        const val API_KEY = ""
        const val SECRET = ""
    }
}
