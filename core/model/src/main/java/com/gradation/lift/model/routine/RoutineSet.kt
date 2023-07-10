package com.gradation.lift.model.routine


data class RoutineSet(
    val id: Int,
    val shortDescription: String,
    val longDescription: String,
    val weekday: Weekday,
)