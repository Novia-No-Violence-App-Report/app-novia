package com.app.novia.ui.welcomepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivityLoginBinding
import com.app.novia.ui.mainactivity.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            //TODO: Login logic
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}