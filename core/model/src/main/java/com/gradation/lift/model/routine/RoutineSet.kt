package com.gradation.lift.model.routine

import com.gradation.lift.model.common.Weekday


data class RoutineSet(
    val id: Int,
    val shortDescription: String,
    val longDescription: String,
    val weekday: Weekday,
)