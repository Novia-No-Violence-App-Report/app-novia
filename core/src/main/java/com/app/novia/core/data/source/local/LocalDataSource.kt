package com.app.novia.core.data.source.local

import com.app.novia.core.data.source.local.dao.EmergencyContactDao
import com.app.novia.core.domain.model.EmergencyContactEntity

class LocalDataSource(
    private val emergencyContactDao: EmergencyContactDao
) {
    suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity) =
        emergencyContactDao.addEmergencyContact(contactEntity)

    suspend fun deleteEmergencyContact(contactEntity: EmergencyContactEntity) =
        emergencyContactDao.deleteEmergencyContact(contactEntity)

    fun updateEmergencyContact(contactEntity: EmergencyContactEntity) =
        emergencyContactDao.updateEmergencyContact(contactEntity)

    fun getAllEmergencyContact() = emergencyContactDao.getAllEmergencyContact()
}