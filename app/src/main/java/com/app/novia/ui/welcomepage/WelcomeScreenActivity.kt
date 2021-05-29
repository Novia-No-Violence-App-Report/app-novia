package com.app.novia.ui.welcomepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivityWelcomeScreenBinding
import com.app.novia.ui.mainactivity.MainActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeScreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityWelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

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
        if(auth.currentUser != null) {
            Intent(this@WelcomeScreenActivity, MainActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

}