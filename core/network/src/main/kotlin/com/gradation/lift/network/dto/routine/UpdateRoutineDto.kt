package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class UpdateRoutineDto(
    @SerialName("routine_id")
    val id: Int? = null,
    @SerialName("routine_set_id")
    val routineSetId: Int,
    @SerialName("work_category")
    val workCategory: String,
    @SerialName("work_weight_list")
    val workWeightList: List<Float>,
    @SerialName("work_repetition_list")
    val workRepetitionList: List<Int>
)
