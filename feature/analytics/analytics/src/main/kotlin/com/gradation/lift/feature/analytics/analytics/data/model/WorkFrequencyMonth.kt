package com.gradation.lift.feature.analytics.analytics.data.model

/**
 * [WorkFrequencyMonth]
 * 월에 대한 운동 횟수 모델
 * @property month 월
 * @property frequency 운동횟수
  */
data class WorkFrequencyMonth(
    val month: Int=0,
    val frequency : Int=0
)