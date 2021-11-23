package com.marcocastope.sowadmovil.model.responses

import com.marcocastope.sowadmovil.model.Incident
import com.squareup.moshi.Json

data class GetIncidentsResponse(
    @field:Json(name = "incidents")
    val incidents: List<Incident> = emptyList()
)