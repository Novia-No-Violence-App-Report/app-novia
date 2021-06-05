package com.app.novia.core.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChatEntity(

    @field:SerializedName("msg")
    val message: String? = null,

    var senderIsBot: Boolean? = false,

    @field:SerializedName("status_code")
    var statusCode: Int? = 0,

    var timeStamp: String = ""
) : Serializable
