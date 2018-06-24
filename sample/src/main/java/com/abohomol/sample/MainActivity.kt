package com.abohomol.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.abohomol.kukot.KuCoin
import kotlinx.android.synthetic.main.activity_main.button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { showUserEmail() }
    }

    private fun showUserEmail() {
        val service = KuCoin.create(API_KEY, SECRET)
        service.getUserProfile()
                .subscribe({
                    Toast.makeText(this, "User email: ${it.email}", Toast.LENGTH_LONG).show()
                }, {
                    Toast.makeText(this, "Request failed", Toast.LENGTH_LONG).show()
                    Log.e("KUCOIN", "Failure", it)
                })
    }

    companion object {
        const val API_KEY = "<your-api-key>"
        const val SECRET = "<your-secret>"
    }
}
