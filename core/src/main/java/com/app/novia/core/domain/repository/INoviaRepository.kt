package com.app.novia.core.domain.repository

import com.app.novia.core.data.source.remote.ApiResponse
import com.app.novia.core.domain.model.ChatEntity
import com.app.novia.core.domain.model.EmergencyContactEntity
import kotlinx.coroutines.flow.Flow

interface INoviaRepository {
    suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity)

    suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity)

    fun updateEmergencnyContact(contactEntity: EmergencyContactEntity)

    fun getEmergencyContacts(): Flow<List<EmergencyContactEntity>>

    suspend fun sendChat(message: String?) : Flow<ApiResponse<ChatEntity>>
}