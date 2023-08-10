package com.gradation.lift.model.routine

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkSet

/**
 * [Routine]
 * 루틴 세트를 생성하기 위한 모델
 * @property id 모델의 고유 아이디
 * @property routineSetId 해당 루틴을 포함하고 있는 루틴세트의 고유 아이디
 * @property workCategory 해당 운동의 종류
 * @property workSetList  해당 운동의 무게 및 횟수 정보
 **/
data class Routine(
    val id: Int,
    val routineSetId: Int,
    val workCategory: WorkCategory,
    val workSetList: List<WorkSet>
)
