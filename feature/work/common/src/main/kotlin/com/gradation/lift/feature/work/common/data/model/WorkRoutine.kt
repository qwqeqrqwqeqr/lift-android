package com.gradation.lift.feature.work.common.data.model

import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * [WorkRoutine]
 * @property id 운동 고유 아이디
 * @property workCategoryId 운동 카테고리 아이디
 * @property workCategoryName 운동 카테고리 이름
 * @property workPart 운동 부위
 * @property workSetList 운동 세트 리스트
 */
data class WorkRoutine(
    val id: Long,
    val workRoutineId: Int,
    val workCategoryId: Int,
    val workCategoryName: String,
    val workPart: List<String>,
    val workSetList: SnapshotStateList<WorkRoutineWorkSet>,
)
