package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.common.*
import com.gradation.lift.model.routine.RoutineSet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetRoutineSetResponseDto(
    @Json(name = "routine_set")
    val routineSet: List<RoutineSetDto>
) {
    fun toRoutineSet(): List<RoutineSet> = this.routineSet.map {
        RoutineSet(
            id = it.id,
            shortDescription = it.shortDescription,
            longDescription = it.longDescription,
            repeatIntervalType = when (it.repeatType) {
                WEEK_DAY_TYPE -> RepeatIntervalType.WeekDayType(weekday = toWeekDay(it.repeatInterval))
                DAY_TYPE -> RepeatIntervalType.DayType(interval = it.repeatInterval)
                else -> null
            }!!
        )
    }
}
