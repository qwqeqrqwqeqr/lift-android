package com.gradation.lift.feature.work.work.data.model

import com.gradation.lift.model.model.date.Weekday

/**
 * [RoutineSetRoutineSelection]
 * [RoutineSelection] 루틴을 리스트로 가지고 있는 모델
 * [WorkRoutineSelection] 으로 매핑하여 운동화면에서 사용한다.
 * @since 2023-08-22 16:22:20
 */
data class RoutineSetRoutineSelection(
    val id: Int,
    val name: String,
    val description: String,
    val weekday: Weekday,
    val routine: List<RoutineSelection>,
    val selected: Boolean,
)