package com.app.novia.core.domain.usecase

import com.app.novia.core.data.source.remote.ApiResponse
import com.app.novia.core.domain.model.ChatEntity
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.core.domain.model.UserResponseEntity
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

interface NoviaUseCase {
    suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity)

    suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity)

    fun updateEmergencnyContact(contactEntity: EmergencyContactEntity)

    fun getEmergencyContacts(): Flow<List<EmergencyContactEntity>>

    suspend fun sendChat(message: JsonObject?): Flow<ApiResponse<ChatEntity>>

    suspend fun addUser(user: JsonObject?): Flow<ApiResponse<UserResponseEntity>>
}