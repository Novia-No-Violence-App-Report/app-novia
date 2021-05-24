package com.app.novia.core.data

import com.app.novia.core.data.source.local.LocalDataSource
import com.app.novia.core.data.source.remote.RemoteDataSource
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.core.domain.repository.INoviaRepository
import com.salomohutapea.movieapp.core.utils.AppExecutors

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INoviaRepository{
    override suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity) {
        localDataSource.addEmergencyContact(contactEntity)
    }

    override suspend fun deleteEmergencnyContact(contactEntity: EmergencyContactEntity) {
        localDataSource.deleteEmergencyContact(contactEntity)
    }

}