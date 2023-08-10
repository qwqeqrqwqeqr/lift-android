package com.gradation.lift.model.routine

import com.gradation.lift.model.common.Weekday


/**
 * [CreateRoutineSetRoutine]
 * 루틴 세트를 생성하기 위한 모델
 * 루틴 세트는 여러 개의 루틴들을 보유하고 있음
 * @property name 루틴 세트 이름
 * @property description 해당 루틴 세트에 대한 설명
 * @property weekday 해당 루틴 세트에 대한 설명
 * @property picture 루틴 세트의 대표 사진
 * @property routine 루틴 세트에 들어가는 루틴들
 */
data class CreateRoutineSetRoutine(
    val name: String,
    val description: String,
    val weekday: List<Weekday>,
    val picture : String,
    val routine : List<CreateRoutine>
)



