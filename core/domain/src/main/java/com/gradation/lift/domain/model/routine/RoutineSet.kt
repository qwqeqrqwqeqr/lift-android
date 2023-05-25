package com.gradation.lift.domain.model.routine

data class RoutineSet(
    val id: Int,
    val short_description: String,
    val long_description: String,
    val repeat_type: Int,
    val repeat_interval: Int
)