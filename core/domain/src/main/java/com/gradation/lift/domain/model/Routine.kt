package com.gradation.lift.domain.model

data class Routine(
    val workCategory : Int,
    val workWeightList: List<Float>,
    val workRepetitionList: List<Int>,
)
