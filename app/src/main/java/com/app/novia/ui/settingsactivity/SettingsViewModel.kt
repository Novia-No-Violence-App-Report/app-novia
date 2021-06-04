package com.app.novia.ui.settingsactivity

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SettingsViewModel : ViewModel() {
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
}