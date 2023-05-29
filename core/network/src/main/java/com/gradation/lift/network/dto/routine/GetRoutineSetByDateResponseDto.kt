package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineSetByDateResponseDto(
    @SerialName("routine_set")
    val routineSet: List<RoutineSetDto>
)
