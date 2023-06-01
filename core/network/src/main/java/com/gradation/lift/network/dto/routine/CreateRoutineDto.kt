package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class CreateRoutineDto(
    @Json(name = "work_category")
    val workCategory: Int,
    @Json(name = "work_weight_list")
    val workWeightList: List<Float>,
    @Json(name = "work_repetition_list")
    val workRepetitionList:  List<Int>
)
