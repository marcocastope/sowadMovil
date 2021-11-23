package com.marcocastope.sowadmovil.model.request

data class UserDataRequest(
    //@field:Json(name = "firstname")
    val firstname: String,
    //@field:Json(name = "lastname")
    val lastname: String,
    //@field:Json(name = "dni")
    val dni: String,
    //@field:Json(name = "username")
    val username: String,
    //@field:Json(name = "password")
    val password: String
)