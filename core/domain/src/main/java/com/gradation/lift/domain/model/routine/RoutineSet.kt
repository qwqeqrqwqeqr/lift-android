package com.gradation.lift.domain.model.routine

import com.gradation.lift.domain.model.common.RepeatIntervalType

data class RoutineSet(
    val id: Int,
    val short_description: String,
    val long_description: String,
    val repeatIntervalType: RepeatIntervalType,
)