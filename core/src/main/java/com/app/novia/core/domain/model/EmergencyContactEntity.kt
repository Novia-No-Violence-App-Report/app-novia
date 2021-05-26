package com.app.novia.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "EmergencyContact")
data class EmergencyContactEntity(

    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo(name = "name") var name: String,

    @ColumnInfo(name = "phone_number") var phoneNumber: String,
) : Serializable {
    constructor(_name: String, _phoneNumber: String) : this(0, _name, _phoneNumber)
}
