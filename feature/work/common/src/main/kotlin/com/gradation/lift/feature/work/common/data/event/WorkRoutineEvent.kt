package com.gradation.lift.feature.work.common.data.event

import com.gradation.lift.feature.work.common.data.event.WorkRoutineEvent.AddWorkSet
import com.gradation.lift.feature.work.common.data.event.WorkRoutineEvent.AppendRoutine
import com.gradation.lift.feature.work.common.data.event.WorkRoutineEvent.RemoveRoutine
import com.gradation.lift.feature.work.common.data.event.WorkRoutineEvent.RemoveWorkSet
import com.gradation.lift.feature.work.common.data.event.WorkRoutineEvent.UpdateWorkSet
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet

/**
 * [RemoveRoutine] 루틴 삭제
 * [AppendRoutine] 루틴 추가
 * [AddWorkSet] 운동 세트 추가
 * [RemoveWorkSet] 운동 세트 삭제
 * [UpdateWorkSet] 운동 세트 업데이트
 * @since 2024-01-21 21:39:56
 */
internal sealed interface WorkRoutineEvent {
    data class RemoveRoutine(val workRoutine: WorkRoutine) : WorkRoutineEvent
    data class AppendRoutine(val workRoutine: WorkRoutine) : WorkRoutineEvent
    data class AddWorkSet(val routineIndex: Int, val workRoutineWorkSet: WorkRoutineWorkSet) :
        WorkRoutineEvent
    data class RemoveWorkSet(val routineIndex: Int, val workRoutineWorkSet: WorkRoutineWorkSet) :
        WorkRoutineEvent
    data class UpdateWorkSet(val routineIndex: Int, val workSetIndex: Int, val workRoutineWorkSet: WorkRoutineWorkSet) :
        WorkRoutineEvent
}