package com.gradation.lift.network.dto.history

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


@JsonClass(generateAdapter = true)
data class HistoryDto(
    @Json(name = "history_id")
    val historyId :Int,
    @Json(name = "comment")
    val comment : String?,
    @Json(name = "score")
    val score :Int,
    @Json(name = "rest_time")
    val restTime : LocalTime,
    @Json(name = "total_time")
    val totalTime : LocalTime,
    @Json(name = "history_time_stamp")
    val historyTimeStamp :LocalDateTime,
    @Json(name = "history_routine")
    val historyRoutine : HistoryRoutineDto,
)
