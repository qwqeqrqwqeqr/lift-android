package com.gradation.lift.feature.work.work.data.model

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.gradation.lift.model.model.work.WorkCategory

/**
 * [WorkRoutine]
 * 현재 운동의 루틴 정보를 담고 있는 모델
 * @property index 현재 루틴의 순번
 * @property workCategory 해당 루틴의 카테고리
 * @property workSetList 해당 루틴의 세트 횟수 목록
 * @since 2023-08-22 16:26:21
 */
data class WorkRoutine(
    val key: Int,
    val workCategory: WorkCategory,
    val workSetList: SnapshotStateList<WorkRoutineWorkSet>
)