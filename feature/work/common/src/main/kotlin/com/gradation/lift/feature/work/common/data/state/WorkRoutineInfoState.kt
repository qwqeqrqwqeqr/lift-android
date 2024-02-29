package com.gradation.lift.feature.work.common.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.feature.work.common.data.event.WorkRoutineInfoEvent
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineCheckedInfo


data class WorkRoutineInfoState(
    var checkedWorkSetList: SnapshotStateList<WorkRoutineCheckedInfo> =
        emptyList<WorkRoutineCheckedInfo>().toMutableStateList(),
    private var openedRoutineList: SnapshotStateList<Int> = emptyList<Int>().toMutableStateList(),
) {

    val checkWorkSet: (WorkRoutineCheckedInfo) -> Unit = {
        onWorkRoutineInfoEvent(WorkRoutineInfoEvent.CheckWorkSet(it))
    }
    val uncheckWorkSet: (WorkRoutineCheckedInfo) -> Unit = {
        onWorkRoutineInfoEvent(WorkRoutineInfoEvent.UncheckWorkSet(it))
    }

    val openRoutineInfo: (Int) -> Unit = {
        onWorkRoutineInfoEvent(WorkRoutineInfoEvent.OpenWorkRoutineInfo(it))
    }

    val closeRoutineInfo: (Int) -> Unit = {
        onWorkRoutineInfoEvent(WorkRoutineInfoEvent.CloseWorkRoutineInfo(it))
    }

    val isChecked: (Int, Int) -> Boolean = { routineId, workSetIndex ->
        checkedWorkSetList.any { it.workRoutineId == routineId && it.workSetIndex == workSetIndex }
    }
    val isOpened: (Int) -> Boolean = { routineId ->
        openedRoutineList.any { it == routineId }
    }
    val isAllCheckedWorkRoutine: (WorkRoutine) -> Boolean = { workRoutine ->
        checkedWorkSetList.count { it.workRoutineId == workRoutine.id } == workRoutine.workSetList.size
    }


    private fun onWorkRoutineInfoEvent(workRoutineInfoEvent: WorkRoutineInfoEvent) {
        when (workRoutineInfoEvent) {
            is WorkRoutineInfoEvent.CheckWorkSet -> {
                checkedWorkSetList.add(workRoutineInfoEvent.workRoutineCheckedInfo)
            }

            is WorkRoutineInfoEvent.UncheckWorkSet -> {
                checkedWorkSetList.remove(workRoutineInfoEvent.workRoutineCheckedInfo)
            }

            is WorkRoutineInfoEvent.OpenWorkRoutineInfo ->
                openedRoutineList.add(workRoutineInfoEvent.routineId)

            is WorkRoutineInfoEvent.CloseWorkRoutineInfo ->
                openedRoutineList.remove(workRoutineInfoEvent.routineId)

        }
    }


}