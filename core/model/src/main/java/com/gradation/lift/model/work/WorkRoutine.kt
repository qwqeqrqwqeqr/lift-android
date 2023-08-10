package com.gradation.lift.model.work

/**
 * [WorkRoutine]
 * 현재 운동 중인 운동에 대한 정보를 나타내는 모델
 * [Work] 모델에 의해서 호출되는 모델
 * @property workId  모델의 고유 아이디 번호
 * @property workCategory 해당 운동의 종류
 * @property workSetList 해당 운동의 무게 및 횟수 정보
 */
data class WorkRoutine(
    val workId: Int,
    val workCategory: WorkCategory,
    val workSetList: List<WorkSet>
)