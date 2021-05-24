package com.app.novia.core.domain.repository

import com.app.novia.core.domain.model.EmergencyContactEntity

interface INoviaRepository {
    suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity)

    suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity)
}