package com.gradation.lift.model.history

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class CreateHistory(
    val comment : String?,
    val score :Int,
    val restTime : LocalTime,
    val totalTime : LocalTime,
    val historyTimeStamp : LocalDate,
    val historyRoutine : List<CreateHistoryRoutine>
)
