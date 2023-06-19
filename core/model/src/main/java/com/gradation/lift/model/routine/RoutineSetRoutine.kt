package com.gradation.lift.model.routine

import com.gradation.lift.domain.model.common.RepeatIntervalType

data class RoutineSetRoutine(
    val id: Int,
    val shortDescription: String,
    val longDescription: String,
    val repeatIntervalType: RepeatIntervalType,
    val routine : List<Routine>
)
