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
            shortDescription = it.shortDescription,
            longDescription = it.longDescription,
            repeatIntervalType = when (it.repeatType) {
                WEEK_DAY_TYPE ->  RepeatIntervalType.WeekDayType(weekday = toWeekDay(it.repeatInterval))
                DAY_TYPE -> RepeatIntervalType.DayType(interval = it.repeatInterval)
                else -> null
            }!!
        )
    }
}
