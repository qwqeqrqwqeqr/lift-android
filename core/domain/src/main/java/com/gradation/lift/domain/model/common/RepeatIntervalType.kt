package com.gradation.lift.domain.model.common


sealed class RepeatIntervalType {
    /**
     * 반복 주기에 대한 타입 클래스
     * [WeekDayType] : 특정 요일에 한하여 반복 (ex 매주 월요일)
     * [DayType] : 특정 일 마다 반복 (ex 3일에 한번씩 반복)
     *
     */
    data class WeekDayType(
        private val type: String = "WD",
        var interval: WeekDay
    ) : RepeatIntervalType()

    data class DayType(
        private val type: String = "D",
        val interval: Int
    ) : RepeatIntervalType()
}



