package com.app.newsapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.newsapp.utils.PreferenceUtils

class SplashActivity : AppCompatActivity() {
    var SPLASH_DELAY_TIMMING: Long = 2000
    protected lateinit var preferenceUtils: PreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }, SPLASH_DELAY_TIMMING)

    }

}
