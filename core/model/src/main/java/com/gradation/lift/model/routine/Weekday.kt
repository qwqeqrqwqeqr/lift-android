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


    fun toWeekDay(weekDay: String): Weekday =
        when (weekDay) {
            MONDAY -> Monday()
            TUESDAY -> Tuesday()
            WEDNESDAY -> Wednesday()
            THURSDAY -> Thursday()
            FRIDAY -> Friday()
            SATURDAY -> Saturday()
            SUNDAY -> Sunday()
            else -> None()
        }

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
}






