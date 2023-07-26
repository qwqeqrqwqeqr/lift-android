package com.gradation.lift.model.routine

import com.gradation.lift.model.work.WorkSet

data class CreateRoutine(
    val workCategoryId: String,
    val workSet: List<WorkSet>
)
