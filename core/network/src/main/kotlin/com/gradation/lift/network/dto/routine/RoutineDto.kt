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
    val workRepetitionList: List<Int>
)

