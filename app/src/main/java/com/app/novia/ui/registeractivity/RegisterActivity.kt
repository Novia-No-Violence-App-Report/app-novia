package com.app.novia.ui.registeractivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.app.novia.core.data.source.remote.ApiResponse
import com.app.novia.core.domain.model.UserModel
import com.app.novia.databinding.ActivityRegisterBinding
import com.app.novia.ui.mainactivity.MainActivity
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private var registeredUser = UserModel()

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
            lifecycleScope.launch {
                val addToDbUser = JsonObject()
                addToDbUser.addProperty("name", registeredUser.name)
                addToDbUser.addProperty("address", registeredUser.address)
                addToDbUser.addProperty("email", registeredUser.email)
                addToDbUser.addProperty("phone", registeredUser.phone)
                addToDbUser.addProperty("user_id", registerViewModel.auth.uid)

                registerViewModel.addUser(addToDbUser).observeOnce(this@RegisterActivity, { response ->
                    if (response != null) {
                        when (response) {
                            is ApiResponse.Success -> {
                                if (response.data.statusCode == 204){
                                    Log.d("API RESPONSE", "SUCCESS ADDING USER DATA")
                                }
                            }
                            is ApiResponse.Empty -> Log.d("API RESPONSE", "EMPTY")
                            is ApiResponse.Error -> Log.d("API RESPONSE", "ERROR ${response.errorMessage}")
                        }
                    }
                })
            }
            Toast.makeText(this@RegisterActivity, it.second, Toast.LENGTH_SHORT).show()
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

            registeredUser = UserModel(name, email, password, address, phone)
            registerViewModel.registerUser(registeredUser)

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

    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }
}