package com.gradation.lift.model.history

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkSet
/**
 * [HistoryRoutine]
 * 운동 기록에 대한 모델입니다.
 * 진행한 운동들의 정보를 담고 있습니다.
 * @property id 해당 모델의 고유 아이디
 * @property historyId 해당 운동 기록 정보를 보유하고 있는 기록 고유 아이디
 * @property workCategory 해당 운동의 카테고리
 * @property workSetList 해당 운동의 무게 및 횟수 정보
 **/
data class HistoryRoutine(
    val id :Int,
    val historyId :Int,
    val workCategory: WorkCategory,
    val workSetList : List<WorkSet>
)
