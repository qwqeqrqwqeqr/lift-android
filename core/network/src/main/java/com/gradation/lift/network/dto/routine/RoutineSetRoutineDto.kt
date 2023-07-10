package com.gradation.lift.network.dto.routine

import com.gradation.lift.network.dto.work.WorkCategoryDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@JsonClass(generateAdapter = true)
data class RoutineSetRoutineDto(
    @Json(name = "routine")
    val routineDto: RoutineDto,
    @Json(name = "routine_set")
    val routineSetDto: RoutineSetDto,
)
