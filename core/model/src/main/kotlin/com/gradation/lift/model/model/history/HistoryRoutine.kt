package com.gradation.lift.model.model.history

import com.gradation.lift.model.model.work.WorkSet

/**
 * [HistoryRoutine]
 * 운동 기록에 대한 모델입니다.
 * 진행한 운동들의 정보를 담고 있습니다.
 * @property id 해당 모델의 고유 아이디
 * @property historyId 해당 운동 기록 정보를 보유하고 있는 기록 고유 아이디
 * @property workCategoryId 해당 운동 카테고리의 아이디
 * @property workCategoryName 운동 이름
 * @property workPart 해당 운동의 운동 부위
 * @property workSetList 해당 운동의 무게 및 횟수 정보
 * @since 2024-03-05 19:12:27
 **/
data class HistoryRoutine(
    val id: Int,
    val historyId: Int,
    val workCategoryId: Int,
    val workCategoryName: String,
    val workPart: List<String>,
    val workSetList: List<WorkSet>,
)
