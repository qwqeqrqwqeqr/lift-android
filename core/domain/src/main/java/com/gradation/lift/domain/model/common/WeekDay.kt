package com.gradation.lift.domain.model.common

sealed class WeekDay {
    data class Monday(val value: Int = 0) : WeekDay()
    data class Tuesday(val value: Int = 1) : WeekDay()
    data class Wednesday(val value: Int = 2) : WeekDay()
    data class Thursday(val value: Int = 3) : WeekDay()
    data class Friday(val value: Int = 4) : WeekDay()
    data class Saturday(val value: Int = 5) : WeekDay()
    data class Sunday(val value: Int = 6) : WeekDay()
}