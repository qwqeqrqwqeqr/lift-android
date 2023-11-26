package com.gradation.lift.feature.routine_detail.routine_list.data.model

import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.getWeekdayEntries

/**
 * @param weekdaySet 필터링 된 요일 집합
 * @since 2023-11-26 21:35:26
 */
internal data class WeekdayFilterType(val weekdaySet: Set<Weekday>) {

    fun getNameList(): List<String> =
        weekdaySet.sortedBy { it.getWeekdayNumber() }.map { it.getWeekdayName() }

    fun contains(weekday: Weekday): Boolean = weekdaySet.contains(weekday)

    fun isCheckedAllWeekday(): Boolean = weekdaySet.size == getWeekdayEntries().size
    fun isCheckedOne(): Boolean = weekdaySet.size == 1
}