package com.gradation.lift.feature.history.analytics.data.model

import com.gradation.lift.model.model.date.Weekday
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


/**
 * [WorkFrequencyWeekDate]
 * 주차 요일 날짜 정보 그리고 해당일자의 운동빈도를 가지고 있는 모델
 * @property week 주차
 * @property weekday 요일 [Weekday] 모델 사용
 * @property date 날짜 정보
 * @since 2023-08-23 20:28:14
 */
data class WorkFrequencyWeekDate(
    val frequency: Int = 0,
    val week: Int = 0,
    val weekday: Weekday = Weekday.None(),
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
) {
    fun weekToText(): String = when (week) {
        1 -> "첫째"
        2 -> "둘째"
        3 -> "셋째"
        4 -> "넷째"
        5 -> "다섯째"
        6 -> "여섯째"
        else -> ""
    }
}

