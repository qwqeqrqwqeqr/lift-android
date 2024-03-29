package com.gradation.lift.feature.createRotuine.updateWorkSet.data.model

/**
 * [CreateWorkSet]
 * 운동 횟수와 무게와 해당 운동의 순번을 저장하고 있는 모델
 * @property setNumber 해당 필드를 통해 리스트 내에서 해당 모델에 접근
 * @since 2023-12-09 21:22:13
 */
data class CreateWorkSet(
    var weight: String,
    var repetition: String,
)




