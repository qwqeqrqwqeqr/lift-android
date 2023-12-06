package com.gradation.lift.feature.create_routine.routine_set.data.model

import com.gradation.lift.model.model.date.Weekday

/**
 * [WeekdaySelection]
 * 요일 선택에 사용되는 모델
 * @since 2023-08-21 13:38:15
 */
internal data class WeekdaySelection(
    val weekday: Weekday = Weekday.None(),
    var selected: Boolean = false,
)