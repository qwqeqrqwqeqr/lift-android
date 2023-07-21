package com.gradation.lift.model.history

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkSet

data class HistoryRoutine(
    val id :Int,
    val historyId :Int,
    val workCategory: WorkCategory,
    val workSetList : List<WorkSet>
)
