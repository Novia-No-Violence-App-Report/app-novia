package com.app.novia.ui.welcomeactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivityWelcomeScreenBinding
import com.app.novia.ui.login.LoginActivity
import com.app.novia.ui.mainactivity.MainActivity
import com.app.novia.ui.registeractivity.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeScreenActivity : AppCompatActivity() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val binding: ActivityWelcomeScreenBinding by lazy {
        ActivityWelcomeScreenBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        with(binding) {
            btnLoginPage.setOnClickListener {
                val intent = Intent(this@WelcomeScreenActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            btnRegisterPage.setOnClickListener {
                val intent = Intent(this@WelcomeScreenActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this@WelcomeScreenActivity, MainActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

}