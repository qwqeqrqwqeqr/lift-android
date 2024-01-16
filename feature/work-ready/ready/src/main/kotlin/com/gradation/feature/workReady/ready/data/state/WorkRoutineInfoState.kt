package com.gradation.feature.workReady.ready.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

/**
 * [WorkRoutineInfoState]
 * 열려있는(상세정보가 보여지는) 루틴들의 정보를 보유하고 있는 상태
 * [openedRoutineList] 현재 펼쳐진(상세 보기) 루틴들의 목록 (루틴 아이디) 형태
 * [selectedRoutineList] 현재 선택된 루틴들의 목록 (루틴 아이디) 형태
 * [isOpened] 파라미터로 전달받은 아이디가 해당 리스트에 존재하는지 확인
 * [isSelected] 파라미터로 전달받은 아이디가 해당 리스트에 존재하는지 확인
 * @since 2024-01-16 13:36:34
 */
internal class WorkRoutineInfoState {

    private var openedRoutineList: SnapshotStateList<Int> = emptyList<Int>().toMutableStateList()
    internal var selectedRoutineList: SnapshotStateList<Int> = emptyList<Int>().toMutableStateList()

    val openRoutineInfo: (Int) -> Unit = {
        onRoutineListInfoEvent(WorkRoutineInfoEvent.OpenWorkRoutineInfo(it))
    }

    val closeRoutineInfo: (Int) -> Unit = {
        onRoutineListInfoEvent(WorkRoutineInfoEvent.CloseWorkRoutineInfo(it))
    }

    val selectRoutineInfo: (Int) -> Unit = {
        onRoutineListInfoEvent(WorkRoutineInfoEvent.SelectWorkRoutineInfo(it))
    }

    val unselectRoutineInfo: (Int) -> Unit = {
        onRoutineListInfoEvent(WorkRoutineInfoEvent.UnselectWorkRoutineInfo(it))
    }
    val clearSelectedRoutineList: () -> Unit = {
        onRoutineListInfoEvent(WorkRoutineInfoEvent.ClearSelectedRoutineList)
    }


    val isOpened: (Int) -> Boolean = { routineId ->
        openedRoutineList.any { it == routineId }
    }
    val isSelected: (Int) -> Boolean = { routineId ->
        selectedRoutineList.any { it == routineId }
    }

    val isNotEmptySelectedRoutineList: () -> Boolean = { selectedRoutineList.isNotEmpty() }

    private fun onRoutineListInfoEvent(workRoutineInfoEvent: WorkRoutineInfoEvent) {
        when (workRoutineInfoEvent) {
            is WorkRoutineInfoEvent.OpenWorkRoutineInfo ->
                openedRoutineList.add(workRoutineInfoEvent.routineId)

            is WorkRoutineInfoEvent.CloseWorkRoutineInfo ->
                openedRoutineList.remove(workRoutineInfoEvent.routineId)


            is WorkRoutineInfoEvent.SelectWorkRoutineInfo ->
                selectedRoutineList.add(workRoutineInfoEvent.routineId)

            is WorkRoutineInfoEvent.UnselectWorkRoutineInfo ->
                selectedRoutineList.remove(workRoutineInfoEvent.routineId)

            is WorkRoutineInfoEvent.ClearSelectedRoutineList ->
                selectedRoutineList.clear()
        }
    }
}


