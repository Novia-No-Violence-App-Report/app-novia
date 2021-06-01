package com.app.novia.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivityLoginBinding
import com.app.novia.ui.mainactivity.MainActivity
import com.app.novia.ui.registeractivity.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        loginViewModel.authResult().observe(this) {
            if (it.isSuccessful) {
                Intent(this@LoginActivity, MainActivity::class.java).also { intent ->
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.txtEmail.error = "Silakan isi e-mail Anda"
                binding.txtEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.txtEmail.error = "E-mail tidak valid"
                binding.txtEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                binding.txtPassword.error = "Password Anda tidak valid"
                binding.txtPassword.requestFocus()
                return@setOnClickListener
            }

            loginViewModel.loginUser(email, password)
        }

        binding.loginQuestion.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}