package com.gradation.lift.feature.work.work.data.model

/**
 * [WorkRoutineWorkSet]
 * 운동 개별 세트에 대한 횟수 무게 및 완료여부를 표시하는 모델
 * @property set  운동 세트
 * @property weight  무게
 * @property repetition  횟수
 * @since 2023-12-11 11:34:40
 */
data class WorkRoutineWorkSet(
    var key:Int,
    val weight: Float,
    val repetition: Int,
)