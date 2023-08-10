package com.gradation.lift.model.work

/**
 * [WorkSet]
 * 운동의 무게와 횟수를 담아둔 모델
 * @property weight  무게 (0.5 단위가 될 수 있기 때문에 float type 반영)
 * @property repetition 횟수
 */
data class WorkSet(
    val weight : Float,
    val repetition : Int
)