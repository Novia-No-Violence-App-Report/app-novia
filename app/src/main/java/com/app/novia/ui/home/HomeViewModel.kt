package com.app.novia.ui.home

import androidx.lifecycle.ViewModel
import com.app.novia.core.domain.usecase.NoviaUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlin.reflect.KProperty

class HomeViewModel constructor(private val useCase: NoviaUseCase) : ViewModel() {
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
}
