package com.app.novia.ui.registeractivity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivityRegisterBinding
import com.app.novia.ui.mainactivity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterActivity : AppCompatActivity() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(
            layoutInflater
        )
    }

    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        registerViewModel.registerResult().observe(this) {
            Toast.makeText(this, it.second, Toast.LENGTH_SHORT).show()
            if (it.first) {
                Intent(this@RegisterActivity, MainActivity::class.java).also { it1 ->
                    it1.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it1)
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.txtEmail.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()
            val name = binding.txtName.text.toString()

            if (email.isEmpty()) {
                binding.txtEmail.error = "E-mail harus diisi!"
                binding.txtEmail.requestFocus()
                return@setOnClickListener
            }

            if (name.isEmpty()) {
                binding.txtEmail.error = "Nama harus diisi!"
                binding.txtEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.txtEmail.error = "E-mail tidak valid!"
                binding.txtEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                binding.txtPassword.error = "Password harus lebih dari 6 karakter!"
                binding.txtPassword.requestFocus()
                return@setOnClickListener
            }

            registerViewModel.registerUser(email, password, name)

        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this@RegisterActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}