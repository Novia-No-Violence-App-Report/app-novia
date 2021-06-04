package com.app.novia.core.domain.usecase

import com.app.novia.core.data.source.remote.ApiResponse
import com.app.novia.core.domain.model.ChatEntity
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.core.domain.model.NewsResponse
import com.app.novia.core.domain.model.UserResponseEntity
import com.app.novia.core.domain.repository.INoviaRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

class NoviaInteractor(private val repository: INoviaRepository) : NoviaUseCase {
    override suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity) {
        repository.addEmergencyContact(contactEntity)
    }

    override suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity) {
        repository.deleteEmergencnyContact(contactEntity)
    }

    override fun updateEmergencnyContact(contactEntity: EmergencyContactEntity) {
        repository.updateEmergencnyContact(contactEntity)
    }

    override fun getEmergencyContacts(): Flow<List<EmergencyContactEntity>> {
        return repository.getEmergencyContacts()
    }

    override suspend fun sendChat(message: JsonObject?): Flow<ApiResponse<ChatEntity>> {
        return repository.sendChat(message)
    }

    override suspend fun addUser(user: JsonObject?): Flow<ApiResponse<UserResponseEntity>> {
        return repository.addUser(user)
    }

    override suspend fun getNews(): Flow<ApiResponse<NewsResponse>> {
        return repository.getNews()
    }
}