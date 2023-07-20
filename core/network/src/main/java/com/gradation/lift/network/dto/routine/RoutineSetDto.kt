package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RoutineSetDto(
    @Json(name = "routine_set_id")
    val routineSetId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "weekday")
    val weekday: String,
    @Json(name = "profile")
    val profile: String? = null,
)


