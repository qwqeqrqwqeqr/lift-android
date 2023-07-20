package com.gradation.lift.network.dto.history

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateHistoryRoutineDto(
    @Json(name = "work_category")
    val workCategory: Int,
    @Json(name = "work_weight_list")
    val workWeightList: List<Float>,
    @Json(name = "work_repetition_list")
    val workRepetitionList:  List<Int>
)
