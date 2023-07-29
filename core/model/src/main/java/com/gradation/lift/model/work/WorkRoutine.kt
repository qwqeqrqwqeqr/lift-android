package com.gradation.lift.model.work

data class WorkRoutine(
    val id : Int,
    val workId : Int,
    val workCategory: WorkCategory,
    val workSetList: List<WorkSet>
)