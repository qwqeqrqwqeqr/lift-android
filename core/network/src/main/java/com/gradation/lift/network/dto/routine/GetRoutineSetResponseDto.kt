package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.common.*
import com.gradation.lift.domain.model.routine.RoutineSet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineSetResponseDto(
    @SerialName("routine_set")
    val routineSet: List<RoutineSetDto>
) {
    fun toRoutineSet(): List<RoutineSet> = this.routineSet.map {
        RoutineSet(
            id = it.id,
            short_description = it.shortDescription,
            long_description = it.longDescription,
            repeatIntervalType = when (it.repeatType) {
                WEEK_DAY_TYPE -> RepeatIntervalType.WeekDayType(
                    weekday = when (it.repeatInterval) {
                        MONDAY -> RepeatIntervalType.WeekDayType.WeekDay.Monday()
                        TUESDAY -> RepeatIntervalType.WeekDayType.WeekDay.Tuesday()
                        WEDNESDAY -> RepeatIntervalType.WeekDayType.WeekDay.Wednesday()
                        THURSDAY -> RepeatIntervalType.WeekDayType.WeekDay.Thursday()
                        FRIDAY ->RepeatIntervalType.WeekDayType.WeekDay.Friday()
                        SATURDAY -> RepeatIntervalType.WeekDayType.WeekDay.Saturday()
                        SUNDAY -> RepeatIntervalType.WeekDayType.WeekDay.Sunday()
                        else -> null
                    }
                )
                DAY_TYPE -> RepeatIntervalType.DayType(interval = it.repeatInterval)
                else -> null
            }
        )
    }
}
