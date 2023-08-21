package com.gradation.lift.create_routine.routine.data.model

/**
 * [IndexWorkSet]
 * 운동 횟수와 무게와 해당 운동의 순번을 저장하고 있는 모델
 * @property index 해당 필드를 통해 리스트 내에서 해당 모델에 접근
 * @since 2023-08-21 23:15:01
 */
data class IndexWorkSet(
    var index: Int, var weight: String, var repetition: String
)




