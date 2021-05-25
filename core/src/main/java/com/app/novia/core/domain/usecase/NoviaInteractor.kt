package com.app.novia.core.domain.usecase

import com.app.novia.core.data.source.remote.ApiResponse
import com.app.novia.core.domain.model.ChatEntity
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.core.domain.repository.INoviaRepository
import kotlinx.coroutines.flow.Flow

class NoviaInteractor(private val repository: INoviaRepository) : NoviaUseCase {
    override suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity) {
        repository.addEmergencyContact(contactEntity)
    }

    override suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity) {
        repository.deleteEmergencnyContact(contactEntity)
    }

    override fun getEmergencyContacts(): Flow<List<EmergencyContactEntity>> {
        return repository.getEmergencyContacts()
    }

    override suspend fun sendChat(message: String?) : Flow<ApiResponse<ChatEntity>> {
        return repository.sendChat(message)
    }
}