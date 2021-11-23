package com.marcocastope.sowadmovil.model

import com.squareup.moshi.Json
import java.util.*

data class Incident(
    @field:Json(name = "idincident")
    val idIncident: Int,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "date")
    val date: String,
    @field:Json(name = "status")
    val status: Status,
    @field:Json(name = "street")
    val street: Street,
    @field:Json(name = "user")
    val user: User
)