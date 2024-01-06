package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoutineSetRoutineDto(
    @SerialName("routine")
    val routineDto: RoutineDto,
    @SerialName("routine_set")
    val routineSetDto: RoutineSetDto,
)
