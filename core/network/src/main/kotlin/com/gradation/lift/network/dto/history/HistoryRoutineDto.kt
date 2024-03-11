package com.gradation.lift.network.dto.history


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class  HistoryRoutineDto(
    @SerialName("history_routine_id")
    val historyRoutineId: Int,
    @SerialName("history_id")
    val historyId: Int,
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
