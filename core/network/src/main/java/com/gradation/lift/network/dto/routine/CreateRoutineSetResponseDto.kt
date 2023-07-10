package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class CreateRoutineSetResponseDto(
    @Json(name = "result")
    val result: Boolean,
)