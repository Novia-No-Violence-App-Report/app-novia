package com.app.novia.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.novia.R
import com.app.novia.databinding.ActivityMainBinding
import com.app.novia.ui.settingsactivity.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d(
                "LLISTENER DEST",
                destination.toString()
            )
            if (destination.label == "Chat aduan") {
                true.chatMode()
            } else {
                false.chatMode()
            }
        }
        binding.fabSos.setOnClickListener {
            navController.navigate(R.id.navigation_sos)
        }
        binding.mainChatBtnBack.setOnClickListener {
            navController.navigate(R.id.navigation_home)
        }
        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun Boolean.chatMode() {
        if (this) {
            binding.fabSos.visibility = View.GONE
            binding.navView.visibility = View.GONE
            binding.mainChatBtnBack.visibility = View.VISIBLE
            binding.mainChatNoviaDesc.visibility = View.VISIBLE
            binding.mainChatNoviaName.visibility = View.VISIBLE
            binding.mainChatNoviaPic.visibility = View.VISIBLE
            binding.appLogo.visibility = View.GONE
            binding.btnSettings.visibility = View.GONE
        } else {
            binding.fabSos.visibility = View.VISIBLE
            binding.navView.visibility = View.VISIBLE
            binding.mainChatBtnBack.visibility = View.INVISIBLE
            binding.mainChatNoviaDesc.visibility = View.GONE
            binding.mainChatNoviaName.visibility = View.GONE
            binding.mainChatNoviaPic.visibility = View.GONE
            binding.appLogo.visibility = View.VISIBLE
            binding.btnSettings.visibility = View.VISIBLE
        }
    }
}