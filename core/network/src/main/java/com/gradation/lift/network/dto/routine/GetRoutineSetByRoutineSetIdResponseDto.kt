package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.common.*
import com.gradation.lift.model.routine.RoutineSet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetRoutineSetByRoutineSetIdResponseDto(
    @Json(name = "routine_set")
    val routine_set: RoutineSetDto
) {
    fun toRoutineSet(): RoutineSet =
        RoutineSet(
            id = this.routine_set.id,
            shortDescription = this.routine_set.shortDescription,
            longDescription = this.routine_set.longDescription,
            repeatIntervalType = when (this.routine_set.repeatType) {
                WEEK_DAY_TYPE -> RepeatIntervalType.WeekDayType(weekday = toWeekDay(this.routine_set.repeatInterval))
                DAY_TYPE -> RepeatIntervalType.DayType(interval = this.routine_set.repeatInterval)
                else -> null
            }!!
        )
}
