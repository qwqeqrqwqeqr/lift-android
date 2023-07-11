package com.gradation.lift.model.routine


sealed class Weekday {
    data class Monday(val name: String = MONDAY) : Weekday()
    data class Tuesday(val name: String = TUESDAY) : Weekday()
    data class Wednesday(val name: String = WEDNESDAY) : Weekday()
    data class Thursday(val name: String = THURSDAY) : Weekday()
    data class Friday(val name: String = FRIDAY) : Weekday()
    data class Saturday(val name: String = SATURDAY) : Weekday()
    data class Sunday(val name: String = SUNDAY) : Weekday()
    data class None(val name: String = NONE) : Weekday()

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

    fun getName(): String = when (val weekday = this) {
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

fun toWeekDay(weekDay: String): Weekday =
    when (weekDay) {
        Weekday.MONDAY -> Weekday.Monday()
        Weekday.TUESDAY -> Weekday.Tuesday()
        Weekday.WEDNESDAY -> Weekday.Wednesday()
        Weekday.THURSDAY -> Weekday.Thursday()
        Weekday.FRIDAY -> Weekday.Friday()
        Weekday.SATURDAY -> Weekday.Saturday()
        Weekday.SUNDAY -> Weekday.Sunday()
        else -> Weekday.None()
    }







