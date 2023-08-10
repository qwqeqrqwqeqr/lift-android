package com.gradation.lift.model.work

import kotlinx.datetime.LocalTime


data class Work(
    val id: Int,
    val workTime : LocalTime,
    val restTime: LocalTime,
    val totalTime: LocalTime,
    val routine: List<WorkRoutine>
)