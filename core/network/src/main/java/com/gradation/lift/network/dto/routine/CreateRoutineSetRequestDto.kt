package com.gradation.lift.network.dto.routine

import com.gradation.lift.model.routine.Weekday
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class CreateRoutineSetRequestDto(
    @Json(name = "short_description")
    val shortDescription: String,
    @Json(name = "long_description")
    val longDescription: String,
    @Json(name = "weekday")
    val weekday: String,
    @Json(name = "routine")
    val routine: List<CreateRoutineDto>
)