package com.gradation.lift.model.routine

import com.gradation.lift.model.common.Weekday


data class RoutineSet(
    val id: Int,
    val name: String,
    val description: String,
    val weekday: Weekday,
)