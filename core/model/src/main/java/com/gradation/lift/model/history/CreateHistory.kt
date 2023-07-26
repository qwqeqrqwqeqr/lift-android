package com.gradation.lift.model.history

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

data class CreateHistory(
    val comment : String?,
    val score :Int,
    val restTime : LocalTime,
    val totalTime : LocalTime,
    val historyTimeStamp : LocalDateTime,
    val historyRoutine : List<CreateHistoryRoutine>
)
