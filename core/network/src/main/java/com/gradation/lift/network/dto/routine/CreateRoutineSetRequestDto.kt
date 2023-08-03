package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class CreateRoutineSetRequestDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "weekday")
    val weekday: List<String>,
    @Json(name = "picture")
    val picture: String,
    @Json(name = "routine")
    val routine: List<CreateRoutineDto>
)