package com.gradation.lift.model.routine

import com.gradation.lift.model.common.Weekday


data class RoutineSetRoutine(
    val id: Int,
    val name: String,
    val description: String,
    val weekday: Weekday,
    val routine : List<Routine>
)
