package com.gradation.lift.feature.workReady.common

import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.feature.workReady.common.model.WorkRoutineWorkSet

/**
 * [RemoveRoutine] 루틴 삭제
 * [RemoveRoutines] 루틴 삭제(다수)
 * [AppendRoutine] 루틴 추가
 * [MoveRoutine] 루틴 이동
 * [Undo] 되돌리기
 * [Clear]  초기화
 * [AddWorkSet] 운동 세트 추가
 * [RemoveWorkSet] 운동 세트 삭제
 * [UpdateWorkSet] 운동 세트 업데이트
 * @since 2024-01-16 12:10:19
 */
internal sealed interface WorkRoutineEvent {
    data class RemoveRoutine(val workRoutine: WorkRoutine) : WorkRoutineEvent
    data class RemoveRoutines(val idList: List<Int>) : WorkRoutineEvent
    data class AppendRoutine(val workRoutine: WorkRoutine) : WorkRoutineEvent
    data class MoveRoutine(val from: Int, val to: Int) : WorkRoutineEvent
    data object Undo : WorkRoutineEvent
    data object Clear : WorkRoutineEvent
    data class AddWorkSet(val routineIndex: Int, val workRoutineWorkSet: WorkRoutineWorkSet) : WorkRoutineEvent
    data class RemoveWorkSet(val routineIndex: Int, val workRoutineWorkSet: WorkRoutineWorkSet) : WorkRoutineEvent
    data class UpdateWorkSet(val routineIndex: Int, val workSetIndex: Int, val workRoutineWorkSet: WorkRoutineWorkSet) : WorkRoutineEvent
}