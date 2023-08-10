package com.gradation.lift.model.history

import com.gradation.lift.model.work.WorkSet

/**
 * [CreateHistoryRoutine]
 * 운동 기록을 저장하기 위해 생성하는 모델입니다.
 * 진행한 운동들의 정보를 담고 있습니다.
 * @property workCategory 해당 운동의 카테고리
 * @property workSetList 해당 운동의 무게 및 횟수 정보
 **/
data class CreateHistoryRoutine(
    val workCategory: String,
    val workSetList: List<WorkSet>
)
