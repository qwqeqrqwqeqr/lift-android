package com.gradation.lift.model.history

import com.gradation.lift.model.work.WorkSet

data class CreateHistoryRoutine(
    val workCategoryId: Int,
    val workSet: List<WorkSet>
)
