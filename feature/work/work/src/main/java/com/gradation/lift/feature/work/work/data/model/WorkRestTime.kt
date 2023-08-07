package com.gradation.lift.feature.work.work.data.model

import kotlinx.datetime.LocalTime

data class WorkRestTime(
    val workTime: LocalTime = LocalTime(0, 0, 0),
    val restTime: LocalTime = LocalTime(0, 0, 0),
    val totalTime: LocalTime = LocalTime(0, 0, 0),
)