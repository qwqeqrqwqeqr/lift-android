package com.gradation.lift.feature.work.work.data.model

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart

data class WorkRoutineSelection(
    val index: Int,
    val workCategory: WorkCategory,
    var opened: Boolean = false,
    val workSetList: List<WorkSetSelection>,
)

val initModel = WorkRoutineSelection(
    index=0,
    workCategory= WorkCategory(0,"", WorkPart(0,""),"",""),
    opened =false,
    workSetList = listOf()
)