package com.gradation.lift.network.dto.history

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class CreateHistoryRoutineDto(
    @SerialName("work_category")
    val workCategory: String,
    @SerialName("work_weight_list")
    val workWeightList: List<Float>,
    @SerialName("work_repetition_list")
    val workRepetitionList:  List<Int>
)
