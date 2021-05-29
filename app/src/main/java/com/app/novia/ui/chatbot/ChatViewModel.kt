package com.app.novia.ui.chatbot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.novia.core.domain.usecase.NoviaUseCase

class ChatViewModel constructor(private val useCase: NoviaUseCase) : ViewModel() {

    suspend fun sendChat(message: String?) = useCase.sendChat(message).asLiveData()

}