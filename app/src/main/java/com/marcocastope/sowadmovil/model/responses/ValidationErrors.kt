package com.marcocastope.sowadmovil.model.responses

import com.squareup.moshi.Json

data class ValidationErrors(
    val firstname: String,
    val lastname: String,
    val dni: String,
    val username: String,
    val password: String
)