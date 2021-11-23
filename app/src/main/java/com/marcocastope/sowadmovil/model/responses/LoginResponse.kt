package com.marcocastope.sowadmovil.model.responses

import com.marcocastope.sowadmovil.model.User

data class LoginResponse(
    val jwt: String,
    val user: User
)