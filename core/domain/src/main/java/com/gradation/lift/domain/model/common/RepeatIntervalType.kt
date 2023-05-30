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
    ) : RepeatIntervalType(){
        sealed class WeekDay {
            data class Monday(val value: Int = MONDAY) : WeekDay()
            data class Tuesday(val value: Int = TUESDAY) : WeekDay()
            data class Wednesday(val value: Int = WEDNESDAY) : WeekDay()
            data class Thursday(val value: Int = THURSDAY) : WeekDay()
            data class Friday(val value: Int = FRIDAY) : WeekDay()
            data class Saturday(val value: Int = SATURDAY) : WeekDay()
            data class Sunday(val value: Int = SUNDAY) : WeekDay()
        }
    }

    data class DayType(
        val type: String = DAY_TYPE,
        val interval: Int
    ) : RepeatIntervalType()
}


const val WEEK_DAY_TYPE = "WD"
const val DAY_TYPE = "D"

const val MONDAY = 0
const val TUESDAY = 1
const val WEDNESDAY = 2
const val THURSDAY = 3
const val FRIDAY = 4
const val SATURDAY = 5
const val SUNDAY = 6
