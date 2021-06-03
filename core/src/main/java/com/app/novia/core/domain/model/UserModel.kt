package com.app.novia.core.domain.model

data class UserModel(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var address: String = "",
    var phone: String = "",
    var uuid: String? = null
)
