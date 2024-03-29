package com.gradation.lift.model.model.routine

import com.gradation.lift.model.model.date.Weekday


/**
 * [CreateRoutineSetRoutine]
 * 루틴 세트를 생성하기 위한 모델
 * 루틴 세트는 여러 개의 루틴들을 보유하고 있음
 * @property name 루틴 세트 이름
 * @property description 해당 루틴 세트에 대한 설명
 * @property weekday 해당 루틴 세트의 요일
 * @property label 해당 루틴 세트의 라벨
 * @property picture 루틴 세트의 대표 사진
 * @property routine 루틴 세트에 들어가는 루틴들
 * @since 2023-10-13 11:13:40
 */
data class CreateRoutineSetRoutine(
    val name: String,
    val description: String,
    val weekday: Set<Weekday>,
    val label: Set<Label>,
    val picture : String,
    val routine : List<CreateRoutine>
)



