package com.gradation.lift.network.dto.routine

import kotlinx.serialization.Serializable


@Serializable
data class GetRoutineByDateResponseDto(
    val id: Int,
    val routine_set_id: Int,
    val routine: RoutineDto,
    val max_weight: Int,
    val min_weight: Int,
    val total_weight: Int,
    val max_repetition: Int,
    val min_repetition: Int,
    val total_repetition: Int
)