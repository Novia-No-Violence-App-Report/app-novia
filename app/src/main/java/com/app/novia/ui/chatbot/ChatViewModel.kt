package com.app.novia.ui.chatbot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.novia.core.domain.usecase.NoviaUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.JsonObject

class ChatViewModel constructor(private val useCase: NoviaUseCase) : ViewModel() {

    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    suspend fun sendChat(message: JsonObject?) = useCase.sendChat(message).asLiveData()
}