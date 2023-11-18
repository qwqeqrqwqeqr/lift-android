package com.gradation.lift.feature.routine_detail.routine_list.data.state

import com.gradation.lift.feature.routine_detail.routine_list.data.state.LabelFilterType.Label

/**
 * [All] 모든 요일 조회
 * [Label] 특정 요일 조회
 * @since 2023-11-18 17:22:32
 */
sealed interface WeekdayFilterType  {
    data object All : WeekdayFilterType
    data class Weekday(val weekdaySet: Set<com.gradation.lift.model.model.date.Weekday>) : WeekdayFilterType
}