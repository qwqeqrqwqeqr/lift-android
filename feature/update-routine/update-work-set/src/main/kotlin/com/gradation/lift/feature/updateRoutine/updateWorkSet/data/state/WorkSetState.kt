package com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.event.WorkSetEvent
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.model.WorkSet
import com.gradation.lift.model.model.work.WorkCategory

class WorkSetState {
    var workSetList: SnapshotStateList<WorkSet> =
        emptyList<WorkSet>().toMutableStateList()

    var workCategory: WorkCategory? by mutableStateOf(null)

    val addWorkSet: () -> Unit = {
        onWorkSetEvent(WorkSetEvent.AddWorkSet)
    }
    val updateWorkSet: (Int, WorkSet) -> Unit = { index, workSet ->
        onWorkSetEvent(WorkSetEvent.UpdateWorkSet(index, workSet))
    }
    val removeWorkSet: (WorkSet) -> Unit = {
        onWorkSetEvent(WorkSetEvent.RemoveWorkSet(it))
    }


    private fun onWorkSetEvent(workSetEvent: WorkSetEvent) {
        when (workSetEvent) {
            WorkSetEvent.AddWorkSet -> {
                workSetList.add(
                    if (workSetList.isEmpty()) WorkSet(1, "", "")
                    else workSetList.last().let { it.copy(setNumber = it.setNumber + 1) }
                )
            }

            is WorkSetEvent.RemoveWorkSet -> {
                workSetList.remove(workSetEvent.workSet)
                workSetList.forEachIndexed { index, workSet ->
                    workSetList[index] = workSet.copy(setNumber = index + 1)
                }
            }

            is WorkSetEvent.UpdateWorkSet -> {
                workSetList[workSetEvent.index] = workSetEvent.workSet
            }
        }
    }
}