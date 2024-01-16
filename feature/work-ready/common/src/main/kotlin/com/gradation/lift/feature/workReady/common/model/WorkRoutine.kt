package com.gradation.lift.feature.workReady.common.model

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.gradation.lift.model.model.work.WorkCategory

/**
 * [WorkRoutine]
 * @param id 운동 고유 아이디
 * @param workCategory 운동 카테고리
 * @param workSetList 운동 세트 리스트
 */
data class WorkRoutine(
    val id : Int,
    val workCategory: WorkCategory,
    val workSetList: SnapshotStateList<WorkRoutineWorkSet>
)
