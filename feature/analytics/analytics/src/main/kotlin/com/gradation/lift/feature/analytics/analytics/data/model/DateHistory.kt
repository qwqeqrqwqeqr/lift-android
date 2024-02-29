package com.gradation.lift.feature.analytics.analytics.data.model

import com.gradation.lift.model.model.history.History
import kotlinx.datetime.LocalDate

/**
 * [DateHistory]
 * @property date 해당 기록의 날짜
 * @property historyList 기록 리스트 기록은 그 날짜의 최신순으로 기록됨
 * @since 2024-02-21 15:37:09
 */
data class DateHistory(
    val date: LocalDate,
    val historyList: List<History>,
)