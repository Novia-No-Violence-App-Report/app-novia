package com.app.novia.ui.sos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.novia.core.domain.usecase.NoviaUseCase

class SosViewModel constructor(private val useCase: NoviaUseCase) : ViewModel() {
    fun getEmergencyContacts() = useCase.getEmergencyContacts().asLiveData()
}