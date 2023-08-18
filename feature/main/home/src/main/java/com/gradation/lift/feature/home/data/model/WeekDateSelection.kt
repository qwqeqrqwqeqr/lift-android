package com.gradation.lift.feature.home.data.model

import com.gradation.lift.model.model.common.Weekday
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

/**
 * [WeekDateSelection]
 * 날짜 및 요일정보와 선택되어있는지에 대한 정보를 동시에 가지고 있는 모델
 * View와 연결지어 사용 (selected, unselected)
 * @since 2023-08-18 18:49:03
 */
internal data class WeekDateSelection(
    var day: String = "",
    val weekday: Weekday = Weekday.None(),
    var localDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    var selected: Boolean = false,
)