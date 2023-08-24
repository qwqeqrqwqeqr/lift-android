package com.gradation.lift.feature.work.routine_selection.data.model

import com.gradation.lift.model.model.date.Weekday
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

/**
 * [WeekDateSelection]
 * @property weekday 요일 정보
 * @property localDate 날짜 정보
 * @property selected 해당 모델이 선택되었는지 여부 (뷰랑 연결지어 사용)
 */
data class WeekDateSelection(
    val weekday: Weekday = Weekday.None(),
    val localDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    var selected: Boolean = false,
)