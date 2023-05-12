package com.gradation.lift.network.dto.routine

import kotlinx.serialization.Serializable

@Serializable
data class RoutineDto(
    val work_category: Int,
    val work_weight_list: List<Float>,
    val work_repetition_list:  List<Int>
)