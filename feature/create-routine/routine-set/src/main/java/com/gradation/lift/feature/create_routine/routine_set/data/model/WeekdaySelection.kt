package com.gradation.lift.feature.create_routine.routine_set.data.model

import com.gradation.lift.model.model.common.Weekday


internal data class WeekdaySelection(
    val weekday: Weekday = Weekday.None(),
    var selected: Boolean = false,
)