package com.gradation.lift.network.dto.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class HistoryDto(
    @SerialName("history_id")
    val historyId :Int,
    @SerialName("comment")
    val comment : String?,
    @SerialName("score")
    val score :Int,
    @SerialName("work_time")
    val workTime : Float,
    @SerialName("rest_time")
    val restTime : Float,
    @SerialName("total_time")
    val totalTime : Float,
    @SerialName("history_time_stamp")
    val historyTimeStamp :String,
    @SerialName("history_routine")
    val historyRoutine : HistoryRoutineDto,
)
