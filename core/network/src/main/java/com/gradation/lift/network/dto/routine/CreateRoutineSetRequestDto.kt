package com.gradation.lift.network.dto.routine

import kotlinx.serialization.Serializable
@Serializable
data class CreateRoutineSetRequestDto(
    val user_id: String,
    val short_description: String,
    val long_description: String,
    val repeat_type: String,
    val repeat_interval: Int,
    val routine: List<RoutineDto>
)