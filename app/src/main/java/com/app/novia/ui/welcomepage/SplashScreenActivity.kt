package com.app.novia.ui.welcomepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.novia.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        //TODO: Check whether user signed in or not
        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashScreenActivity, WelcomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}