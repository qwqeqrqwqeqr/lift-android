package com.gradation.lift.network.dto.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateHistoryRequestDto(
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("score")
    val score: Int? = null,
    @SerialName("progress")
    val progress: Int,
    @SerialName("work_time")
    val workTime: Int,
    @SerialName("rest_time")
    val restTime: Int,
    @SerialName("total_time")
    val totalTime: Int,
    @SerialName("history_time_stamp")
    val historyTimeStamp: String,
    @SerialName("history_routine")
    val historyRoutine: List<CreateHistoryRoutineDto>,
)
