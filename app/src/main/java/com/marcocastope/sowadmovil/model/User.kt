package com.marcocastope.sowadmovil.model

data class User(
    val iduser: Int,
    val firstname: String,
    val lastname: String,
    val dni: String,
    val username: String,
    val password: String,
    val role: Role
)
