package com.gradation.lift.feature.updateRoutine.updateWorkSet.data.event

import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.event.WorkSetEvent.AddWorkSet
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.event.WorkSetEvent.RemoveWorkSet
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.event.WorkSetEvent.UpdateWorkSet
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.model.CreateWorkSet


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
    data class RemoveWorkSet(val createWorkSet: CreateWorkSet) : WorkSetEvent
    data class UpdateWorkSet(val index: Int, val createWorkSet: CreateWorkSet) : WorkSetEvent
}