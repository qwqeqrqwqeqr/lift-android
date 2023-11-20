package com.gradation.lift.feature.routine_detail.routine_list.data.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.gradation.lift.feature.routine_detail.routine_list.data.event.RoutineListInfoEvent
import com.gradation.lift.feature.routine_detail.routine_list.data.model.RoutineIdInfo

/**
 * [RoutineListInfoState]
 * 루틴 리스트의 정보를 가지고 있는 상태
 * @param openedRoutineList 현재 펼쳐진(상세 보기) 루틴들의 목록 (루틴리스트 아이디, 루틴 아이디) 형태
 * @since 2023-11-19 15:21:26
 */
data class RoutineListInfoState(
    val openedRoutineList: MutableState<List<RoutineIdInfo>> = mutableStateOf(emptyList())
) {

    val openRoutineInfo: (RoutineIdInfo) -> Unit = {
        onRoutineListInfoEvent(RoutineListInfoEvent.OpenRoutineInfo(it))
    }

    val closeRoutineInfo: (RoutineIdInfo) -> Unit = {
        onRoutineListInfoEvent(RoutineListInfoEvent.CloseRoutineInfo(it))
    }

    private fun onRoutineListInfoEvent(routineListInfoEvent: RoutineListInfoEvent) {
        when (routineListInfoEvent) {
            is RoutineListInfoEvent.CloseRoutineInfo ->
                openedRoutineList.value =
                    openedRoutineList.value.minus(routineListInfoEvent.routineIdInfo)

            is RoutineListInfoEvent.OpenRoutineInfo ->
                openedRoutineList.value =
                    openedRoutineList.value.plus(routineListInfoEvent.routineIdInfo)
        }
    }
}


