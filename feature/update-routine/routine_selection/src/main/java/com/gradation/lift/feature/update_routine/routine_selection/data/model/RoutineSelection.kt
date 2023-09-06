package com.gradation.lift.feature.update_routine.routine_selection.data.model

import com.gradation.lift.model.model.routine.Routine

/**
 * [RoutineSelection]
 * 루틴과 해당 루틴의 상세정보가 열려있는지에 대한 정보를 담고있는 모델
 * 뷰와 연결지어 사용
 * @since 2023-09-06 21:09:55
 */
data class RoutineSelection(
    val routine: Routine,
    val opened: Boolean
)