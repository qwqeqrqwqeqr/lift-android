package com.gradation.lift.domain.model.routine

import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkSet

data class Routine(
    val id: Int,
    val routine_set_id: Int,
    val workCategory : WorkCategory,
    val workSetList : List<WorkSet>,
    val max_weight: Float,
    val min_weight: Float,
    val total_weight: Float,
    val max_repetition: Int,
    val min_repetition: Int,
    val total_repetition: Int,
)
