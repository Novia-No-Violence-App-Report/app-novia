package com.app.novia.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.novia.core.data.source.local.dao.EmergencyContactDao
import com.app.novia.core.domain.model.EmergencyContactEntity

@Database(
    entities = [EmergencyContactEntity::class], version = 1, exportSchema = false
)

abstract class NoviaDatabase : RoomDatabase() {

    abstract fun emergencyContactDao(): EmergencyContactDao

}