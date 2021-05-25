package com.app.novia.core.domain.usecase

import com.app.novia.core.domain.model.EmergencyContactEntity
import kotlinx.coroutines.flow.Flow

interface NoviaUseCase {
    suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity)

    suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity)

    fun getEmergencyContacts(): Flow<List<EmergencyContactEntity>>
}