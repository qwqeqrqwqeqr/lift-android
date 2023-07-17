package com.gradation.lift.feature.ready_work.selection.data

import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.model.user.UserDetail
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

internal data class WeekdayCard(
    val weekday: Weekday = Weekday.None(),
    var selected: Boolean = false,
)





