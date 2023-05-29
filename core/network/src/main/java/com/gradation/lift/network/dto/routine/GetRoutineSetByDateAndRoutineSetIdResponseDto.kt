package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetRoutineSetByDateAndRoutineSetIdResponseDto(
    @SerialName("routine_set")
    val routine_set: RoutineSetDto
)
