package com.gradation.lift.network.dto.history

import com.gradation.lift.network.dto.work.WorkCategoryDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class  HistoryRoutineDto(
    @Json(name = "history_routine_id")
    val historyRoutineId :Int,
    @Json(name = "history_id")
    val historyId :Int,
    @Json(name = "work_category")
    val workCategory: WorkCategoryDto,
    @Json(name = "work_weight_list")
    val workWeightList: List<Float>,
    @Json(name = "work_repetition_list")
    val workRepetitionList: List<Int>,
)
