package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetRoutineSetRoutineResponseDto(
    @SerialName("routine_set_routine")
    val routineSetRoutine: List<RoutineSetRoutineDto>
)
