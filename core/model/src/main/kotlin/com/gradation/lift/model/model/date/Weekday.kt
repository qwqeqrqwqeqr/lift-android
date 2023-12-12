package com.gradation.lift.model.model.date

import kotlinx.datetime.*

/**
 * [Weekday]
 * 요일을 표현하는 모델
 */
sealed class Weekday {
    data class Monday(
        val number: Int = 1,
        val value: String = MONDAY_VALUE,
        val name: String = MONDAY_NAME_VALUE
    ) : Weekday()

    data class Tuesday(
        val number: Int = 2,
        val value: String = TUESDAY_VALUE,
        val name: String = TUESDAY_NAME_VALUE
    ) : Weekday()

    data class Wednesday(
        val number: Int = 3,
        val value: String = WEDNESDAY_VALUE,
        val name: String = WEDNESDAY_NAME_VALUE
    ) : Weekday()

    data class Thursday(
        val number: Int = 4,
        val value: String = THURSDAY_VALUE,
        val name: String = THURSDAY_NAME_VALUE
    ) : Weekday()

    data class Friday(
        val number: Int = 5,
        val value: String = FRIDAY_VALUE,
        val name: String = FRIDAY_NAME_VALUE
    ) : Weekday()

    data class Saturday(
        val number: Int = 6,
        val value: String = SATURDAY_VALUE,
        val name: String = SATURDAY_NAME_VALUE
    ) : Weekday()

    data class Sunday(
        val number: Int = 7,
        val value: String = SUNDAY_VALUE,
        val name: String = SUNDAY_NAME_VALUE
    ) : Weekday()

    data class None(
        val number: Int = 0,
        val value: String = NONE_VALUE,
        val name: String = NONE_NAME_VALUE
    ) : Weekday()

    companion object {
        const val MONDAY_VALUE = "Mon"
        const val TUESDAY_VALUE = "Tue"
        const val WEDNESDAY_VALUE = "Wed"
        const val THURSDAY_VALUE = "Thu"
        const val FRIDAY_VALUE = "Fri"
        const val SATURDAY_VALUE = "Sat"
        const val SUNDAY_VALUE = "Sun"
        const val NONE_VALUE = ""

        const val MONDAY_NAME_VALUE = "월"
        const val TUESDAY_NAME_VALUE = "화"
        const val WEDNESDAY_NAME_VALUE = "수"
        const val THURSDAY_NAME_VALUE = "목"
        const val FRIDAY_NAME_VALUE = "금"
        const val SATURDAY_NAME_VALUE = "토"
        const val SUNDAY_NAME_VALUE = "일"
        const val NONE_NAME_VALUE = ""
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

    fun getWeekdayNumber(): Int = when (val weekday = this) {
        is Friday -> weekday.number
        is Monday -> weekday.number
        is None -> weekday.number
        is Saturday -> weekday.number
        is Sunday -> weekday.number
        is Thursday -> weekday.number
        is Tuesday -> weekday.number
        is Wednesday -> weekday.number
    }
}

/**
 * [toWeekDay]
 * LocalDate 또는 String 을 Weekday 클래스로 전환합니다.
 */
fun String.toWeekDay(): Weekday =
    when (this) {
        Weekday.MONDAY_VALUE -> Weekday.Monday()
        Weekday.TUESDAY_VALUE -> Weekday.Tuesday()
        Weekday.WEDNESDAY_VALUE -> Weekday.Wednesday()
        Weekday.THURSDAY_VALUE -> Weekday.Thursday()
        Weekday.FRIDAY_VALUE -> Weekday.Friday()
        Weekday.SATURDAY_VALUE -> Weekday.Saturday()
        Weekday.SUNDAY_VALUE -> Weekday.Sunday()
        else -> Weekday.None()
    }

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


/**
 * [Weekday]에 대한 entries를 구합니다.
 * @param startMonday 월요일이 시작점인지에 대한 여부 (아닐 경우 일요일 시작)
 * @since 2023-11-26 16:34:00
 */
fun getWeekdayEntries(startMonday: Boolean = true): List<Weekday> =
    if (startMonday) listOf(
        Weekday.Monday(),
        Weekday.Tuesday(),
        Weekday.Wednesday(),
        Weekday.Thursday(),
        Weekday.Friday(),
        Weekday.Saturday(),
        Weekday.Sunday()
    ) else listOf(
        Weekday.Sunday(),
        Weekday.Monday(),
        Weekday.Tuesday(),
        Weekday.Wednesday(),
        Weekday.Thursday(),
        Weekday.Friday(),
        Weekday.Saturday()
    )
