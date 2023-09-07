package com.gradation.lift.feature.update_routine.routine_selection.data.model

import com.gradation.lift.model.model.date.Weekday

/**
 * [RoutineSetRoutineSelection]
 * [RoutineSelection] 루틴을 리스트로 가지고 있는 모델
 * @since 2023-09-06 21:10:05
 */
data class RoutineSetRoutineSelection(
    val id: Int,
    val name: String,
    val description: String,
    val weekday: Weekday,
    val picture: String,
    val routine: List<RoutineSelection>,
)