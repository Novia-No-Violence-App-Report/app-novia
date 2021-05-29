package com.app.novia.ui.settingsactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivitySettingsBinding
import com.app.novia.ui.welcomepage.WelcomeScreenActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {
    private val viewModel : SettingsViewModel by viewModel()
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.settingsBtnBack.setOnClickListener {
            finish()
        }

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            Intent(this@SettingsActivity, WelcomeScreenActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}