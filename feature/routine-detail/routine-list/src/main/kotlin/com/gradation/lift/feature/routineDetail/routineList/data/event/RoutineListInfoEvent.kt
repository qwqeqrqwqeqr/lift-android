package com.gradation.lift.feature.routineDetail.routineList.data.event

import com.gradation.lift.feature.routineDetail.routineList.data.model.RoutineIdInfo


/**
 * [RoutineListInfoEvent]
 * 루틴 리스트의 정보를 업데이트 하는 이벤드 모음
 * [OpenRoutineInfo] 루틴 상세 관련 정보를 엽니다.
 * [CloseRoutineInfo] 루틴 상세 관련 정보를 닫습니다.
 * @since 2023-11-20 21:20:42
 */
internal sealed interface RoutineListInfoEvent {

    data class OpenRoutineInfo(val routineIdInfo: RoutineIdInfo) : RoutineListInfoEvent
    data class CloseRoutineInfo(val routineIdInfo: RoutineIdInfo) : RoutineListInfoEvent
}