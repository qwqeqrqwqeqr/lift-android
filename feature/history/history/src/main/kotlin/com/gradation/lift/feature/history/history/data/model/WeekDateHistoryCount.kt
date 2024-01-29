package com.gradation.lift.feature.history.history.data.model

import com.gradation.lift.model.model.date.WeekDateMonth

/**
 * [WeekDateHistoryCount]]
 * @property historyCount 기록의 개수
 */
data class WeekDateHistoryCount(
    val historyCount: Int = 0,
    val weekDateMonth: WeekDateMonth,
)
