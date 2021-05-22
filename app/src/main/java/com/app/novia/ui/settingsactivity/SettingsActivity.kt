package com.app.novia.ui.settingsactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.novia.R
import com.app.novia.databinding.ActivityMainBinding
import com.app.novia.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingsBtnBack.setOnClickListener {
            finish()
        }

    }
}