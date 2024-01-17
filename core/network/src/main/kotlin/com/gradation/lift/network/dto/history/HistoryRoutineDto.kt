package com.gradation.lift.network.dto.history

import com.gradation.lift.network.dto.work.WorkCategoryDto
import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class  HistoryRoutineDto(
    @SerialName("history_routine_id")
    val historyRoutineId :Int,
    @SerialName("history_id")
    val historyId :Int,
    @SerialName("work_category")
    val workCategory: WorkCategoryDto,
    @SerialName("work_weight_list")
    val workWeightList: List<Float>,
    @SerialName("work_repetition_list")
    val workRepetitionList: List<Int>,
)
