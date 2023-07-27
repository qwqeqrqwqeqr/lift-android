package com.gradation.lift.model.routine

import com.gradation.lift.model.work.WorkSet

data class CreateRoutine(
    val workCategory: String,
    val workSetList: List<WorkSet>
)
