package com.marcocastope.sowadmovil.model.responses

data class GenericResponse(
    val timestamp: Long,
    val status: Int,
    val message: String,
    val url: String,
    val validationErrors: ValidationErrors
)