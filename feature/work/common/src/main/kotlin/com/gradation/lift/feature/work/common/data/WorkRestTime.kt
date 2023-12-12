package com.gradation.lift.feature.work.common.data

import kotlinx.datetime.LocalTime

/**
 * [WorkRestTime]
 * @property workTime 운동시간
 * @property restTime 휴식시간
 * @property totalTime 총시간
 */
data class WorkRestTime(
    val workTime: LocalTime = LocalTime(0, 0, 0),
    val restTime: LocalTime = LocalTime(0, 0, 0),
    val totalTime: LocalTime = LocalTime(0, 0, 0),
)