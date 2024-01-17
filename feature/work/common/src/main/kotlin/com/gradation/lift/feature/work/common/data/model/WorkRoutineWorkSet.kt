package com.gradation.lift.feature.work.common.data.model


/**
 * [WorkRoutineWorkSet]
 * 운동 개별 세트에 대한 횟수 무게 및 완료여부를 표시하는 모델
 * @property weight  무게
 * @property repetition  횟수
 * @since 2024-01-15 21:26:50
 */

data class WorkRoutineWorkSet(
    var weight: String = "",
    var repetition: String = "",
)