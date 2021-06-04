package com.app.novia.core

import com.app.novia.core.data.source.local.LocalDataSource
import com.app.novia.core.data.source.remote.ApiResponse
import com.app.novia.core.data.source.remote.RemoteDataSource
import com.app.novia.core.domain.model.ChatEntity
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.core.domain.model.NewsResponse
import com.app.novia.core.domain.model.UserResponseEntity
import com.app.novia.core.domain.repository.INoviaRepository
import com.app.novia.core.utils.AppExecutors
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INoviaRepository {
    override suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity) {
        localDataSource.addEmergencyContact(contactEntity)
    }

    override suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity) {
        localDataSource.deleteEmergencyContact(contactEntity)
    }

    override fun updateEmergencnyContact(contactEntity: EmergencyContactEntity) {
        appExecutors.diskIO().execute { localDataSource.updateEmergencyContact(contactEntity) }
    }

    override fun getEmergencyContacts(): Flow<List<EmergencyContactEntity>> {
        return localDataSource.getAllEmergencyContact()
    }

    override suspend fun sendChat(message: JsonObject?): Flow<ApiResponse<ChatEntity>> {
        return remoteDataSource.sendChat(message)
    }

    override suspend fun addUser(user: JsonObject?): Flow<ApiResponse<UserResponseEntity>> {
        return remoteDataSource.addUser(user)
    }

    override suspend fun getNews(): Flow<ApiResponse<NewsResponse>> {
        return remoteDataSource.getNews()
    }
}