package com.gradation.lift.feature.workReady.routineSelection.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.feature.workReady.routineSelection.data.event.RoutineListInfoEvent
import com.gradation.lift.feature.workReady.routineSelection.data.model.RoutineIdInfo

/**
 * [RoutineListInfoState]
 * 루틴 리스트의 정보를 가지고 있는 상태
 * [openedRoutineList] 현재 펼쳐진(상세 보기) 루틴들의 목록 (루틴리스트 아이디, 루틴 아이디) 형태
 * [selectedRoutineList] 현재 선택된 루틴들의 목록
 * [isOpened] 파라미터로 전달받은 아이디가 해당 리스트에 존재하는지 확인합니다.
 * [isSelected] 파라미터로 전달받은 아이디가 해당 리스트에 존재하는지 확인합니다.
 * @since 2023-12-02 15:37:16
 */
internal class RoutineListInfoState {

    private var openedRoutineList: SnapshotStateList<RoutineIdInfo> =
        emptyList<RoutineIdInfo>().toMutableStateList()

    val selectedRoutineList: SnapshotStateList<Int> =
        emptyList<Int>().toMutableStateList()


    val openRoutineInfo: (RoutineIdInfo) -> Unit = {
        onRoutineListInfoEvent(RoutineListInfoEvent.OpenRoutineInfo(it))
    }

    val closeRoutineInfo: (RoutineIdInfo) -> Unit = {
        onRoutineListInfoEvent(RoutineListInfoEvent.CloseRoutineInfo(it))
    }

    val selectRoutine: (Int) -> Unit = {
        onRoutineListInfoEvent(RoutineListInfoEvent.SelectRoutine(it))
    }

    val unselectRoutine: (Int) -> Unit = {
        onRoutineListInfoEvent(RoutineListInfoEvent.UnselectRoutine(it))
    }

    val isOpened: (Int, Int?) -> Boolean = { routineListId, routineId ->
        openedRoutineList.any { it.routineListId == routineListId && it.routineId == routineId }
    }

    val isSelected: (Int) -> Boolean = { routineListId ->
        selectedRoutineList.any { it == routineListId }
    }


    private fun onRoutineListInfoEvent(routineListInfoEvent: RoutineListInfoEvent) {
        when (routineListInfoEvent) {
            is RoutineListInfoEvent.CloseRoutineInfo ->
                openedRoutineList.remove(routineListInfoEvent.routineIdInfo)

            is RoutineListInfoEvent.OpenRoutineInfo ->
                openedRoutineList.add(routineListInfoEvent.routineIdInfo)

            is RoutineListInfoEvent.UnselectRoutine ->
                selectedRoutineList.remove(routineListInfoEvent.routineSetId)

            is RoutineListInfoEvent.SelectRoutine ->
                selectedRoutineList.add(routineListInfoEvent.routineSetId)
        }
    }
}


