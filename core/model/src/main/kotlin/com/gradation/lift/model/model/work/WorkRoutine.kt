package com.gradation.lift.model.model.work

import java.util.UUID

/**
 * [WorkRoutine]
 * 현재 운동 중인 운동에 대한 정보를 나타내는 모델
 * [Work] 모델에 의해서 호출되는 모델
 * @property workId  모델의 고유 아이디 번호
 * @property workCategoryId 해당 운동 카테고리의 아이디
 * @property workCategoryName 운동 이름
 * @property workPart 해당 운동의 운동 부위
 * @property workSetList 해당 운동의 무게 및 횟수 정보
 */
data class WorkRoutine(
    val workId: UUID,
    val workCategoryId: Int,
    val workCategoryName: String,
    val workPart: List<String>,
    val workSetList: List<WorkSet>,
)