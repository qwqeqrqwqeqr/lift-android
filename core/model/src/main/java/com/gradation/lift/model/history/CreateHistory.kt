package com.gradation.lift.model.history

import kotlinx.datetime.LocalTime

data class CreateHistory(
    val comment : String?,
    val score :Int,
    val restTime : LocalTime,
    val totalTime : LocalTime,
    val historyRoutine : List<CreateHistoryRoutine>
)
