package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.common.*
import com.gradation.lift.domain.model.routine.RoutineSet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetRoutineSetByRoutineSetIdResponseDto(
    @SerialName("routine_set")
    val routine_set: RoutineSetDto
) {
    fun toRoutineSet(): RoutineSet =
        RoutineSet(
            id = this.routine_set.id,
            short_description = this.routine_set.shortDescription,
            long_description = this.routine_set.longDescription,
            repeatIntervalType = when (this.routine_set.repeatType) {
                WEEK_DAY_TYPE -> RepeatIntervalType.WeekDayType(
                    weekday = when (this.routine_set.repeatInterval) {
                        MONDAY -> RepeatIntervalType.WeekDayType.WeekDay.Monday()
                        TUESDAY -> RepeatIntervalType.WeekDayType.WeekDay.Tuesday()
                        WEDNESDAY -> RepeatIntervalType.WeekDayType.WeekDay.Wednesday()
                        THURSDAY -> RepeatIntervalType.WeekDayType.WeekDay.Thursday()
                        FRIDAY -> RepeatIntervalType.WeekDayType.WeekDay.Friday()
                        SATURDAY -> RepeatIntervalType.WeekDayType.WeekDay.Saturday()
                        SUNDAY -> RepeatIntervalType.WeekDayType.WeekDay.Sunday()
                        else -> null
                    }
                )
                DAY_TYPE -> RepeatIntervalType.DayType(interval = this.routine_set.repeatInterval)
                else -> null
            }
        )
}
