package com.marcocastope.sowadmovil.model.responses

import com.marcocastope.sowadmovil.model.Street

data class StreetResponse(
    val streets: List<Street> = emptyList()
)