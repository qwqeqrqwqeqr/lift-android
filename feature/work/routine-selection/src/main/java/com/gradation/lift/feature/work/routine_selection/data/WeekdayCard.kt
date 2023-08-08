package com.gradation.lift.feature.work.routine_selection.data

import com.gradation.lift.model.common.Weekday
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

data class WeekdayCard(
    val weekday: Weekday = Weekday.None(),
    val localDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    var selected: Boolean = false,
)