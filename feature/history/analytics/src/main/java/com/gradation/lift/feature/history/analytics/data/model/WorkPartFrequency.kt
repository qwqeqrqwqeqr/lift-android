package com.gradation.lift.feature.history.analytics.data.model


/**
 * [WorkPartFrequency]
 * 운동 부위 별 빈도 수를 나타내는 모델
 * @since 2023-09-05 16:02:48
 */
data class WorkPartFrequency(
    val chestFrequency : Int = 0,
    val shoulderFrequency : Int = 0,
    val armFrequency : Int = 0,
    val backFrequency : Int = 0,
    val lowerBodyFrequency : Int = 0,
    val absFrequency : Int = 0,
)