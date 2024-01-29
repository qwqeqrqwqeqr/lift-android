package com.gradation.lift.feature.work.createWorkSet.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet
import com.gradation.lift.feature.work.createWorkSet.data.event.WorkSetEvent

class WorkSetState {
    var workSetList: SnapshotStateList<WorkRoutineWorkSet> =
        emptyList<WorkRoutineWorkSet>().toMutableStateList()


    val addWorkSet: () -> Unit = {
        onWorkSetEvent(WorkSetEvent.AddWorkSet)
    }
    val updateWorkSet: (Int, WorkRoutineWorkSet) -> Unit = { index, workSet ->
        onWorkSetEvent(WorkSetEvent.UpdateWorkSet(index, workSet))
    }
    val removeWorkSet: (WorkRoutineWorkSet) -> Unit = {
        onWorkSetEvent(WorkSetEvent.RemoveWorkSet(it))
    }


    private fun onWorkSetEvent(workSetEvent: WorkSetEvent) {
        when (workSetEvent) {
            WorkSetEvent.AddWorkSet -> {
                workSetList.add(
                    if (workSetList.isEmpty()) WorkRoutineWorkSet("", "")
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