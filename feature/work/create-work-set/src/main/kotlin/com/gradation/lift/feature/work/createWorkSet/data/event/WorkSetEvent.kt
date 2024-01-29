package com.gradation.lift.feature.work.createWorkSet.data.event

import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet


/**
 * [WorkSetEvent]
 * 운동 세트 정보 수정 관련 이벤트
 * [AddWorkSet] 세트 추가
 * [RemoveWorkSet] 세트 삭제
 * [UpdateWorkSet] 해당 세트 값 (무게 또는 횟수) 업데이트
 * @since 2023-12-08 11:28:56
 */
internal sealed interface WorkSetEvent {
    data object AddWorkSet : WorkSetEvent
    data class RemoveWorkSet(val workSet: WorkRoutineWorkSet) : WorkSetEvent
    data class UpdateWorkSet(val index: Int,val workSet : WorkRoutineWorkSet) : WorkSetEvent
}