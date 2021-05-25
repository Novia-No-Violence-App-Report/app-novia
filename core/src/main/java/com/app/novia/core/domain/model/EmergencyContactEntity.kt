package com.app.novia.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "EmergencyContact")
data class EmergencyContactEntity(
    @ColumnInfo(name = "name")
    val name: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
) : Serializable
