package com.gradation.lift.model.routine

import com.gradation.lift.model.work.WorkSet

/**
 * [CreateRoutine]
 * 루틴을 생성하기 위한 모델
 * [CreateRoutineSetRoutine]을 통해 모델을 불러온다.
 * @property workCategory 운동 종류에 대한 이름
 * @property workSetList 해당 운동의 무게 및 횟수 정보
 */
data class CreateRoutine(
    val workCategory: String,
    val workSetList: List<WorkSet>
)
