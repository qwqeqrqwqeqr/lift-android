package com.gradation.lift.feature.routine_detail.routine_list.data.event


/**
 * [RoutineListInfoEvent]
 * 루틴 리스트의 정보를 업데이트 하는 이벤드 모음
 * [OpenRoutineInfo] 루틴 상세 관련 정보를 엽니다.
 * [CloseRoutineInfo] 루틴 상세 관련 정보를 닫습니다.
 * @since 2023-11-19 15:25:26
 */
sealed interface RoutineListInfoEvent {

    data class OpenRoutineInfo(val id: Pair<Int, Int>) : RoutineListInfoEvent
    data class CloseRoutineInfo(val id: Pair<Int, Int>) : RoutineListInfoEvent
}