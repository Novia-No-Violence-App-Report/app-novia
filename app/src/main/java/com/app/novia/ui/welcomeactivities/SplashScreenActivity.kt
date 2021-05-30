package com.app.novia.ui.welcomeactivities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //TODO: Check whether user signed in or not
        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashScreenActivity, WelcomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}