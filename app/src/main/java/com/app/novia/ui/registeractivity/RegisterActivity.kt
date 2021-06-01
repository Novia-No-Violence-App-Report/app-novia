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
            val name = binding.txtName.text.toString()
            val email = binding.txtEmail.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()
            val phone = binding.txtPhoneNum.text.toString()
            val address = binding.txtAddress.text.toString()

            if (name.isEmpty()) {
                binding.txtName.error = "Masukkan nama Anda"
                binding.txtName.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.txtEmail.error = "E-mail wajib diisi"
                binding.txtEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.txtEmail.error = "Format e-mail tidak valid"
                binding.txtEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                binding.txtPassword.error = "Password harus lebih dari 6 karakter"
                binding.txtPassword.requestFocus()
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                binding.txtAddress.error = "Masukkan alamat rumah Anda"
                binding.txtAddress.requestFocus()
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                binding.txtPhoneNum.error = "Masukkan nomor ponsel Anda"
                binding.txtPhoneNum.requestFocus()
                return@setOnClickListener
            }

            registerViewModel.registerUser(name, email, password, address, phone)

        }
    }

    override fun onStart() {
        super.onStart()
        if (registerViewModel.auth.currentUser != null) {
            Intent(this@RegisterActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}