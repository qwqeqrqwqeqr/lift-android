package com.gradation.lift.feature.work.work.data.model

import com.gradation.lift.model.work.WorkCategory

data class WorkRoutineSelection(
    val index: Int,
    val workCategory: WorkCategory,
    var opened: Boolean = false,
    val workSetList: List<WorkSetSelection>,
)