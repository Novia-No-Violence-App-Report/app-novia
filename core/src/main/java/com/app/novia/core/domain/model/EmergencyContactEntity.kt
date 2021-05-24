package com.app.novia.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "EmergencyContact")
data class EmergencyContactEntity(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: Long? = null,
) : Serializable
