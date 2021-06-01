package com.app.novia.ui.home

import androidx.lifecycle.ViewModel
import com.app.novia.core.domain.usecase.NoviaUseCase
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel constructor(private val useCase: NoviaUseCase) : ViewModel() {
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
}
