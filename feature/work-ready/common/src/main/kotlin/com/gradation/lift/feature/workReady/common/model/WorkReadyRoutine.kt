package com.gradation.lift.feature.workReady.common.model

import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * [WorkReadyRoutine]
 * @property id 운동 고유 아이디
 * @property workCategoryId 해당 운동 카테고리의 아이디
 * @property workCategoryName 운동 이름
 * @property workPart 해당 운동의 운동 부위
 * @property workSetList 운동 세트 리스트
 */
data class WorkReadyRoutine(
    val id: Int,
    val workCategoryId: Int,
    val workCategoryName: String,
    val workPart: List<String>,
    val workSetList: SnapshotStateList<WorkReadyRoutineWorkSet>,
)
