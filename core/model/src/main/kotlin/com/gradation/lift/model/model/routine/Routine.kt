package com.gradation.lift.model.model.routine

import com.gradation.lift.model.model.work.WorkSet

/**
 * [Routine]
 * 루틴 세트를 생성하기 위한 모델
 * @property id 모델의 고유 아이디
 * @property routineSetId 해당 루틴을 포함하고 있는 루틴세트의 고유 아이디
 * @property workCategoryId 해당 운동 카테고리의 아이디
 * @property workCategoryName 운동 이름
 * @property workPart 해당 운동의 운동 부위
 * @property workSetList  해당 운동의 무게 및 횟수 정보
 * @since 2024-03-05 19:12:27
 **/
data class Routine(
    val id: Int?,
    val routineSetId: Int,
    val workCategoryId: Int,
    val workCategoryName: String,
    val workPart: List<String>,
    val workSetList: List<WorkSet>,
)
