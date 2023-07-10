package com.gradation.lift.model.routine


data class RoutineSetRoutine(
    val id: Int,
    val shortDescription: String,
    val longDescription: String,
    val weekday: Weekday,
    val routine : List<Routine>
)
