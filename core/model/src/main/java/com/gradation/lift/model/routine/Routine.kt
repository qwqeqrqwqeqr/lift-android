package com.gradation.lift.model.routine

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkSet

data class Routine(
    val id: Int,
    val routineSetId: Int,
    val workCategory : WorkCategory,
    val workSetList : List<WorkSet>,
    val maxWeight: Float,
    val minWeight: Float,
    val totalWeight: Float,
    val maxRepetition: Int,
    val minRepetition: Int,
    val totalRepetition: Int,
)
