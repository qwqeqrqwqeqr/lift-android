package com.gradation.feature.workReady.ready.data.state



/**
 * [WorkRoutineInfoEvent]
 * 열려있는 운동 정보를 업데이트 하는 이벤드 모음
 * [OpenWorkRoutineInfo] 운동 정보를 펼칩니다.
 * [CloseWorkRoutineInfo] 운동 정보를 닫습니다.
 * [SelectWorkRoutineInfo] 운동 정보를 선택 합니다.
 * [UnselectWorkRoutineInfo] 운동 정보를 선택 취소 합니다.
 * @since 2023-11-20 21:20:42
 */
internal sealed interface WorkRoutineInfoEvent {

    data class OpenWorkRoutineInfo(val routineId: Int) : WorkRoutineInfoEvent
    data class CloseWorkRoutineInfo(val routineId: Int) : WorkRoutineInfoEvent
    data class SelectWorkRoutineInfo(val routineId: Int) : WorkRoutineInfoEvent
    data class UnselectWorkRoutineInfo(val routineId: Int) : WorkRoutineInfoEvent
    data object ClearSelectedRoutineList: WorkRoutineInfoEvent
}