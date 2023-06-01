package com.gradation.lift.network.dto.routine

import com.gradation.lift.network.dto.work.WorkCategoryDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RoutineSetRoutineDto(
    @SerialName("routine_set_id")
    val id: Int,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("long_description")
    val longDescription: String,
    @SerialName("repeat_type")
    val repeatType: String,
    @SerialName("repeat_interval")
    val repeatInterval: Int,
    @SerialName("routine_id")
    val routineId: Int,
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
