package com.app.novia.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "TemporaryChat")
data class ChatEntity(
    @ColumnInfo(name = "message")
    @field:SerializedName("msg")
    val message: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "sender")
    var senderIsBot: Boolean? = false,

    @PrimaryKey
    @ColumnInfo(name = "timeStamp")
    var timeStamp: String
) : Serializable
