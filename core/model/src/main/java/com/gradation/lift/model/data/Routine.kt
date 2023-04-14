package com.gradation.lift.model.data

data class Routine(
    val workCategory : Int,
    val workWeightList: List<Float>,
    val workRepetitionList: List<Int>,
)
