package com.gradation.lift.model.model.work

/**
 * [WorkSet]
 * 운동의 무게와 횟수를 담아둔 모델
 * @property workSetId 운동 세트의 아이디, 해당 아이디를 바탕으로 엑세스 할 수 있음
 * @property weight  무게 (0.5 단위가 될 수 있기 때문에 float type 반영)
 * @property repetition 횟수
 */
data class WorkSet(
    var workSetId: Int,
    val weight: Float,
    val repetition: Int,
)