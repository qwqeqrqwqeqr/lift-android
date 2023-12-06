package com.gradation.lift.model.model.routine

import com.gradation.lift.model.model.work.WorkSet

/**
 * [UpdateRoutine]
 * 루틴을 업데이트하기 위한 모델
 * [UpdateRoutineSetRoutine]을 통해 모델을 불러온다.
 * @property id 루틴의 아이디, null인 경우 해당 루틴은 새로 만든 루틴임을 의미함
 * @property workCategory 운동 종류에 대한 이름
 * @property workSetList 해당 운동의 무게 및 횟수 정보
 * @since 2023-09-06 16:17:18
 */
data class UpdateRoutine(
    val id: Int?,
    val workCategory: String,
    val workSetList: List<WorkSet>
)


