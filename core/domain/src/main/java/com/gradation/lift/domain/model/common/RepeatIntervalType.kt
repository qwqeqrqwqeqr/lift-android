package com.gradation.lift.domain.model.common


sealed class RepeatIntervalType {
    /**
     * 반복 주기에 대한 타입 클래스
     * [WeekDayType] : 특정 요일에 한하여 반복 (ex 매주 월요일)
     * [DayType] : 특정 일 마다 반복 (ex 3일에 한번씩 반복)
     *
     */
    data class WeekDayType(
        val type: String = WEEK_DAY_TYPE,
        val weekday: WeekDay
    ) : RepeatIntervalType() {

    }

    data class DayType(
        val type: String = DAY_TYPE,
        val interval: Int
    ) : RepeatIntervalType()
}


sealed class WeekDay {
    object Monday : WeekDay()
    object Tuesday : WeekDay()
    object Wednesday : WeekDay()
    object Thursday : WeekDay()
    object Friday : WeekDay()
    object Saturday : WeekDay()
    object Sunday : WeekDay()
}


fun toWeekDay(weekDay: Int): WeekDay =
    when (weekDay) {
        MONDAY -> WeekDay.Monday
        TUESDAY -> WeekDay.Tuesday
        WEDNESDAY -> WeekDay.Wednesday
        THURSDAY -> WeekDay.Thursday
        FRIDAY -> WeekDay.Friday
        SATURDAY -> WeekDay.Saturday
        SUNDAY -> WeekDay.Sunday
        else -> null}!!


const val WEEK_DAY_TYPE = "WD"
const val DAY_TYPE = "D"

const val MONDAY = 0
const val TUESDAY = 1
const val WEDNESDAY = 2
const val THURSDAY = 3
const val FRIDAY = 4
const val SATURDAY = 5
const val SUNDAY = 6
