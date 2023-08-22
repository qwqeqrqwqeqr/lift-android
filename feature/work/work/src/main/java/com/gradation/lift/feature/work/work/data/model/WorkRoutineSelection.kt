package com.gradation.lift.feature.work.work.data.model

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart

data class WorkRoutineSelection(
    val index: Int= 0,
    val workCategory: WorkCategory = WorkCategory(0,"", WorkPart(0,""),"",""),
    var opened: Boolean = false,
    val workSetList: List<WorkSetSelection> = listOf(),
)