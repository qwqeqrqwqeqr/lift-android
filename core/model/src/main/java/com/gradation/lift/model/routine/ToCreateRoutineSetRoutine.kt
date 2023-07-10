package com.gradation.lift.model.routine

import com.gradation.lift.model.work.WorkSet

data class CreateRoutineSetRoutine(
    val shortDescription: String,
    val longDescription: String,
    val weekday: Weekday,
    val routine : List<CreateRoutineSetRoutineDetail>
)

data class CreateRoutineSetRoutineDetail(
    val workCategoryId: Int,
    val workSet: List<WorkSet>
)


