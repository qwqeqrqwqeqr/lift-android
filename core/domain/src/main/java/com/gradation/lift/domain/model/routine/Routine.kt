package com.gradation.lift.domain.model.routine

data class Routine(
    val workCategory : Int,
    val workWeightList: List<Float>,
    val workRepetitionList: List<Int>,
)
