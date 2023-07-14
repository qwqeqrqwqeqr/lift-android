package com.gradation.lift.model.common

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.*


sealed class Weekday {
    data class Monday(val value: String = MONDAY, val name: String = "월") : Weekday()
    data class Tuesday(val value: String = TUESDAY, val name: String = "화") : Weekday()
    data class Wednesday(val value: String = WEDNESDAY, val name: String = "수") : Weekday()
    data class Thursday(val value: String = THURSDAY, val name: String = "목") : Weekday()
    data class Friday(val value: String = FRIDAY, val name: String = "금") : Weekday()
    data class Saturday(val value: String = SATURDAY, val name: String = "토") : Weekday()
    data class Sunday(val value: String = SUNDAY, val name: String = "일") : Weekday()
    data class None(val value: String = NONE, val name: String = "") : Weekday()

    companion object {
        const val MONDAY = "Monday"
        const val TUESDAY = "Tuesday"
        const val WEDNESDAY = "Wednesday"
        const val THURSDAY = "Thursday"
        const val FRIDAY = "Friday"
        const val SATURDAY = "Saturday"
        const val SUNDAY = "Sunday"
        const val NONE = "None"
    }

    fun getWeekdayValue(): String = when (val weekday = this) {
        is Friday -> weekday.value
        is Monday -> weekday.value
        is None -> weekday.value
        is Saturday -> weekday.value
        is Sunday -> weekday.value
        is Thursday -> weekday.value
        is Tuesday -> weekday.value
        is Wednesday -> weekday.value
    }

    fun getWeekdayName(): String = when (val weekday = this) {
        is Friday -> weekday.name
        is Monday -> weekday.name
        is None -> weekday.name
        is Saturday -> weekday.name
        is Sunday -> weekday.name
        is Thursday -> weekday.name
        is Tuesday -> weekday.name
        is Wednesday -> weekday.name
    }
}

fun String.toWeekDay(): Weekday =
    when (this) {
        Weekday.MONDAY -> Weekday.Monday()
        Weekday.TUESDAY -> Weekday.Tuesday()
        Weekday.WEDNESDAY -> Weekday.Wednesday()
        Weekday.THURSDAY -> Weekday.Thursday()
        Weekday.FRIDAY -> Weekday.Friday()
        Weekday.SATURDAY -> Weekday.Saturday()
        Weekday.SUNDAY -> Weekday.Sunday()
        else -> Weekday.None()
    }

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toWeekday(): Weekday =
    when (this.dayOfWeek) {
        DayOfWeek.MONDAY -> Weekday.Monday()
        DayOfWeek.TUESDAY -> Weekday.Tuesday()
        DayOfWeek.WEDNESDAY -> Weekday.Wednesday()
        DayOfWeek.THURSDAY -> Weekday.Thursday()
        DayOfWeek.FRIDAY -> Weekday.Friday()
        DayOfWeek.SATURDAY -> Weekday.Saturday()
        DayOfWeek.SUNDAY -> Weekday.Sunday()
        else -> Weekday.None()
    }





