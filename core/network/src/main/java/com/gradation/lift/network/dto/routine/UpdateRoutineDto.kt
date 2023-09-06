package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateRoutineDto(
    @Json(name = "routine_id")
    val id: Int?,
    @Json(name = "work_category")
    val workCategory: String,
    @Json(name = "work_weight_list")
    val workWeightList: List<Float>,
    @Json(name = "work_repetition_list")
    val workRepetitionList:  List<Int>
)
