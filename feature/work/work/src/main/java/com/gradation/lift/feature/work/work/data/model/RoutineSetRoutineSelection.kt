package com.gradation.lift.feature.work.work.data.model

import com.gradation.lift.model.model.common.Weekday

data class RoutineSetRoutineSelection(
    val id: Int,
    val name: String,
    val description: String,
    val weekday: Weekday,
    val routine: List<RoutineSelection>,
    val selected: Boolean,
)