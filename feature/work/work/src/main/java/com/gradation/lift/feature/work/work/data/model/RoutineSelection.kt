package com.gradation.lift.feature.work.work.data.model

import com.gradation.lift.model.model.routine.Routine

/**
 * [RoutineSelection]
 * 루틴과 해당 루틴의 상세정보가 열려있는지에 대한 정보를 담고있는 모델
 * 뷰와 연결지어 사용
 * @since 2023-08-22 16:21:28
 */
data class RoutineSelection(
    val routine: Routine,
    val opened: Boolean
)