package com.app.novia.core.data.source.local.dao

import androidx.room.*
import com.app.novia.core.domain.model.EmergencyContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmergencyContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmergencyContact(contactEntity: EmergencyContactEntity)

    @Delete
    suspend fun deleteEmergencyContact(contactEntity: EmergencyContactEntity)

    @Query("SELECT * from EmergencyContact")
    fun getAllEmergencyContact(): Flow<List<EmergencyContactEntity>>
}