package com.app.novia.core.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponseEntity (
    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("user_id")
    var userId: String? = null,

    @field:SerializedName("status_code")
    var statusCode: Int? = 0,
) : Serializable