package com.gradation.lift.feature.work.common.data.event

import com.gradation.lift.feature.work.common.data.event.WorkRoutineInfoEvent.CheckWorkSet
import com.gradation.lift.feature.work.common.data.event.WorkRoutineInfoEvent.UncheckWorkSet
import com.gradation.lift.model.model.work.CheckedWorkSetInfo

/**
 * [CheckWorkSet] 운동 체크
 * [UncheckWorkSet] 운동 체크 취소
 * @since 2024-01-21 21:56:45
 */
internal sealed interface WorkRoutineInfoEvent {
    data class CheckWorkSet(val checkedWorkSetInfo: CheckedWorkSetInfo) :
        WorkRoutineInfoEvent

    data class UncheckWorkSet(val checkedWorkSetInfo: CheckedWorkSetInfo) :
        WorkRoutineInfoEvent

    data class OpenWorkRoutineInfo(val routineId: Int) : WorkRoutineInfoEvent
    data class CloseWorkRoutineInfo(val routineId: Int) : WorkRoutineInfoEvent
}