package com.app.novia.ui.welcomepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivityRegisterBinding
import com.app.novia.ui.mainactivity.MainActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            //TODO: Register logic
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}