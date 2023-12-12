package com.gradation.lift.network.dto.history

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.serializers.LocalDateTimeComponentSerializer
import kotlinx.datetime.serializers.LocalTimeComponentSerializer
import kotlinx.serialization.Serializable


@JsonClass(generateAdapter = true)
data class CreateHistoryRequestDto(
    @Json(name = "comment")
    val comment : String?,
    @Json(name = "score")
    val score :Int,

    @Json(name = "work_time")
    val workTime : Int,

    @Json(name = "rest_time")
    val restTime : Int,

    @Json(name = "total_time")
    val totalTime : Int,

    @Json(name = "history_time_stamp")
    val historyTimeStamp : String,
    @Json(name = "history_routine")
    val historyRoutine : List<CreateHistoryRoutineDto>
)
