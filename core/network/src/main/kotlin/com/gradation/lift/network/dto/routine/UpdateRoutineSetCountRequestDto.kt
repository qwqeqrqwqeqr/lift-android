package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UpdateRoutineSetCountRequestDto(
    @Json(name = "routine_set_id")
    val id: Int,
)