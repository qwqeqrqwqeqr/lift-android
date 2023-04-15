package com.gradation.lift.network.dto.routine

import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineSetResponseDto(
    val id: Int,
    val user_id: String,
    val short_description: String,
    val long_description: String,
    val repeat_type: Int,
    val repeat_interval: Int
)