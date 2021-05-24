package com.app.novia.core.domain.usecase

import com.app.novia.core.domain.model.EmergencyContactEntity


interface NoviaUseCase {
    suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity)

    suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity)
}