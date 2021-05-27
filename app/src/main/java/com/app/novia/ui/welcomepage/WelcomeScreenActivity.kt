package com.app.novia.ui.welcomepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivityWelcomeScreenBinding

class WelcomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
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
}