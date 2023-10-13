package com.gradation.lift.model.model.routine

import com.gradation.lift.model.model.date.Weekday


/**
 * [UpdateRoutineSetRoutine]
 * 루틴 세트를 업데이트하기 위한 모델
 * 루틴 세트는 여러 개의 루틴들을 보유하고 있음
 * @property id 루틴 세트의 아이디
 * @property name 루틴 세트 이름
 * @property description 해당 루틴 세트에 대한 설명
 * @property weekday 해당 루틴세트가 적용된 요일
 * @property label 해당 루틴세트가 적용된 라벨
 * @property picture 루틴 세트의 대표 사진
 * @property routine 루틴 세트에 들어가는 루틴들
 * @since 2023-10-13 11:15:00
 */
data class UpdateRoutineSetRoutine(
    val id: Int = 0,
    var name: String = "",
    var description: String = "",
    var weekday: List<Weekday> = emptyList(),
    var label: List<Label> = emptyList(),
    var picture: String = "",
    val routine: List<UpdateRoutine> = emptyList()
)



