package com.gradation.lift.model.model.routine

import kotlinx.datetime.LocalDateTime


/**
 * [UpdateUsedRoutineSet]
 * 루틴세트 사용횟수를 증가 시키기 위한 모델
 * @property routineSetIdList 루틴세트의 아이디
 * @property usedTimeStamp 루틴세트를 사용한 시간대
 * @since 2024-02-18 16:54:11
 */
data class UpdateUsedRoutineSet(
    val routineSetIdList: List<Int>,
    val usedTimeStamp: LocalDateTime,
)
