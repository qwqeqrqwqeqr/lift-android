package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RoutineSetDto(
    @Json(name = "routine_set_id")
    val routineSetId: Int,
    @Json(name = "short_description")
    val shortDescription: String,
    @Json(name = "long_description")
    val longDescription: String,
    @Json(name = "weekday")
    val weekday: String
)


