package com.marcocastope.sowadmovil.model.request

data class IncidentDataRequest(
    val idincident: Int? = null,
    val description: String? = null,
    val street: Int? = null,
    val user: Int? = null
)