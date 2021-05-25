package com.app.novia.ui.contactlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.core.domain.usecase.NoviaUseCase

class ContactListViewModel constructor(private val useCase: NoviaUseCase) : ViewModel() {
    suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity) =
        useCase.addEmergencyContact(contactEntity)

    suspend fun deleteEmergencyContact(contactEntity: EmergencyContactEntity) =
        useCase.deleteEmergencnyContact(contactEntity)

    fun getEmergencyContacts() = useCase.getEmergencyContacts().asLiveData()
}