package com.gradation.lift.domain.model.routine

import com.gradation.lift.domain.model.work.WorkCategory

data class Routine(
    val id: Int,
    val routine_set_id: Int,
    val workCategory : Int,
    val workWeightList: List<Float>,
    val workRepetitionList: List<Int>,
    val max_weight: Int,
    val min_weight: Int,
    val total_weight: Int,
    val max_repetition: Int,
    val min_repetition: Int,
    val total_repetition: Int,
)
