package com.gradation.lift.network.dto.routine

import com.gradation.lift.network.dto.work.WorkCategoryDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoutineDto(
    val id: Int,
    @SerialName("routine_set_id")
    val routineSetId: Int,
    @SerialName("work_category")
    val workCategory: WorkCategoryDto,
    @SerialName("work_weight_list")
    val workWeightList: List<Float>,
    @SerialName("work_repetition_list")
    val workRepetitionList: List<Int>,
    @SerialName("max_weight")
    val maxWeight: Float,
    @SerialName("min_weight")
    val minWeight: Float,
    @SerialName("total_weight")
    val totalWeight: Float,
    @SerialName("max_repetition")
    val maxRepetition: Int,
    @SerialName("min_repetition")
    val minRepetition: Int,
    @SerialName("total_repetition")
    val totalRepetition: Int
)
