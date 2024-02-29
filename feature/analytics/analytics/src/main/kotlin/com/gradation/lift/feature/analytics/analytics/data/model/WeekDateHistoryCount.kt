package com.gradation.lift.feature.analytics.analytics.data.model

import com.gradation.lift.model.model.date.WeekDateMonth


/**
 * [WeekDateHistoryCount]
 * 해당 날짜에 대한 히스토리 개수
 * @since 2024-02-21 15:34:59
 */
data class WeekDateHistoryCount(
    val historyCount: Int = 0,
    val weekDateMonth: WeekDateMonth,
)
