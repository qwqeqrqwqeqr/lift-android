package com.gradation.lift.model.model.routine

import com.gradation.lift.model.model.date.Weekday


/**
 * [RoutineSetRoutine]
 * 루틴 세트에 대한 정보를 포함하고 있는 모델
 * 루틴 세트는 여러 개의 루틴들을 보유하고 있음
 * @property id 모델의 고유 아이디
 * @property name 루틴 세트 이름
 * @property description 루틴 세트에 대한 설명
 * @property weekday 해당 루틴 세트가 적용되는 요일
 * @property picture 루틴 세트의 대표 사진
 * @property routine 루틴 세트에 들어가는 루틴들
 **/
data class RoutineSetRoutine(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val weekday: Set<Weekday>,
    val picture: String = "",
    val label: Set<Label>,
    val count: Int = 0,
    val routine: List<Routine> = emptyList()
)
