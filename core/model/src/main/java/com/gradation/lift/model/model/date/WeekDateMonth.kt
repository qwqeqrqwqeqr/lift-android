package com.gradation.lift.model.model.date

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

/**
 * [WeekDateMonth]
 * 월 주차 요일 날짜 정보를 가지고 있는 모델
 * @property week 주차
 * @property weekday 요일 [Weekday] 모델 사용
 * @property date 날짜 정보
 * @since 2023-08-23 20:28:14
 */
data class WeekDateMonth(
    val month :Int =0,
    val week: Int = 0,
    val weekday: Weekday = Weekday.None(),
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
)
