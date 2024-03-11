package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoutineDto(
    @SerialName("routine_id")
    val routineId: Int,
    @SerialName("routine_set_id")
    val routineSetId: Int,
    @SerialName("work_category_id")
    val workCategoryId: Int,
    @SerialName("work_category_name")
    val workCategoryName: String,
    @SerialName("work_part")
    val workPart: List<String>,
    @SerialName("work_weight_list")
    val workWeightList: List<Float>,
    @SerialName("work_repetition_list")
    val workRepetitionList: List<Int>,
)



