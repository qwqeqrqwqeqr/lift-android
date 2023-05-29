package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineSetResponseDto(
    @SerialName("routine_set")
    val routineSet: List<RoutineSetDto>
)