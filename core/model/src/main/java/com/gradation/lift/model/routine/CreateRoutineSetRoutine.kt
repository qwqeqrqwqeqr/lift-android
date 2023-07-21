package com.gradation.lift.model.routine

import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.work.WorkSet

data class CreateRoutineSetRoutine(
    val name: String,
    val description: String,
    val weekday: List<Weekday>,
    val profile : String?,
    val routine : List<CreateRoutine>
)



