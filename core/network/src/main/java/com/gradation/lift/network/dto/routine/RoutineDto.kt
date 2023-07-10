package com.gradation.lift.network.dto.routine

import com.gradation.lift.network.dto.work.WorkCategoryDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class RoutineDto(
    @Json(name = "routine_id")
    val routineId: Int,
    @Json(name = "routine_set_id")
    val routineSetId: Int,
    @Json(name = "work_category")
    val workCategory: WorkCategoryDto,
    @Json(name = "work_weight_list")
    val workWeightList: List<Float>,
    @Json(name = "work_repetition_list")
    val workRepetitionList: List<Int>,
    @Json(name = "max_weight")
    val maxWeight: Float,
    @Json(name = "min_weight")
    val minWeight: Float,
    @Json(name = "total_weight")
    val totalWeight: Float,
    @Json(name = "max_repetition")
    val maxRepetition: Int,
    @Json(name = "min_repetition")
    val minRepetition: Int,
    @Json(name = "total_repetition")
    val totalRepetition: Int
)


