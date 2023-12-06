package com.gradation.lift.feature.history.daily_log.data.model

import com.gradation.lift.model.model.date.Weekday
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


/**
 * [HistoryScoreWeekDate]
 * 주차 요일 날짜 정보 그리고 해당일자에 운동한 운동 점수 가지고 있는 모델
 * @property score 점수
 * @property weekday 요일 [Weekday] 모델 사용
 * @property date 날짜 정보
 * @since 2023-08-23 20:28:14
 */
data class HistoryScoreWeekDate(
    val score: Int = 0,
    val week: Int = 0,
    val weekday: Weekday = Weekday.None(),
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
)