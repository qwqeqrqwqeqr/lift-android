package com.gradation.lift.feature.workReady.routineSelection.data.event

import com.gradation.lift.feature.workReady.routineSelection.data.model.RoutineIdInfo


/**
 * [RoutineListInfoEvent]
 * 루틴 리스트의 정보를 업데이트 하는 이벤드 모음
 * [OpenRoutineInfo] 루틴 상세 관련 정보를 엽니다.
 * [CloseRoutineInfo] 루틴 상세 관련 정보를 닫습니다.
 * [SelectRoutine] 루틴을 선택합니다.
 * [UnselectRoutine] 루틴 선택을 취소합니다.
 * @since 2023-12-10 22:02:26
 */
internal sealed interface RoutineListInfoEvent {

    data class OpenRoutineInfo(val routineIdInfo: RoutineIdInfo) : RoutineListInfoEvent
    data class CloseRoutineInfo(val routineIdInfo: RoutineIdInfo) : RoutineListInfoEvent
    data class SelectRoutine(val routineSetId: Int) : RoutineListInfoEvent
    data class UnselectRoutine(val routineSetId: Int) : RoutineListInfoEvent
}