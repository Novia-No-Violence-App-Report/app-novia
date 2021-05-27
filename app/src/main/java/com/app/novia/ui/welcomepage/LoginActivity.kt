package com.app.novia.ui.welcomepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.novia.R
import com.app.novia.databinding.ActivityLoginBinding
import com.app.novia.databinding.ActivityMainBinding
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