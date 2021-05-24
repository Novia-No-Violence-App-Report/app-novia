package com.app.novia.core.domain.usecase

import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.core.domain.repository.INoviaRepository

class NoviaInteractor(private val repository: INoviaRepository) : NoviaUseCase {
    override suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity) {
        repository.addEmergencyContact(contactEntity)
    }

    override suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity) {
        repository.deleteEmergencnyContact(contactEntity)
    }
}