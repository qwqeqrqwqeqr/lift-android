package com.gradation.lift.feature.workReady.createWorkSet.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutineWorkSet
import com.gradation.lift.feature.workReady.createWorkSet.data.event.WorkSetEvent

class WorkSetState {
    var workSetList: SnapshotStateList<WorkReadyRoutineWorkSet> =
        emptyList<WorkReadyRoutineWorkSet>().toMutableStateList()


    val addWorkSet: () -> Unit = {
        onWorkSetEvent(WorkSetEvent.AddWorkSet)
    }
    val updateWorkSet: (Int, WorkReadyRoutineWorkSet) -> Unit = { index, workSet ->
        onWorkSetEvent(WorkSetEvent.UpdateWorkSet(index, workSet))
    }
    val removeWorkSet: (WorkReadyRoutineWorkSet) -> Unit = {
        onWorkSetEvent(WorkSetEvent.RemoveWorkSet(it))
    }


    private fun onWorkSetEvent(workSetEvent: WorkSetEvent) {
        when (workSetEvent) {
            WorkSetEvent.AddWorkSet -> {
                workSetList.add(
                    if (workSetList.isEmpty()) WorkReadyRoutineWorkSet("", "")
                    else workSetList.last()
                )
            }

            is WorkSetEvent.RemoveWorkSet -> {
                workSetList.remove(workSetEvent.workSet)
            }

            is WorkSetEvent.UpdateWorkSet -> {
                workSetList[workSetEvent.index] = workSetEvent.workSet
            }
        }
    }
}