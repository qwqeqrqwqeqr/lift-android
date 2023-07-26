package com.gradation.lift.model.history

import com.gradation.lift.model.work.WorkSet

data class CreateHistoryRoutine(
    val workCategory: String,
    val workSet: List<WorkSet>
)
