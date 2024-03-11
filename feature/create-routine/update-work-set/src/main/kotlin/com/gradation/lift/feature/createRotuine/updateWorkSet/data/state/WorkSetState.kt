package com.gradation.lift.feature.createRotuine.updateWorkSet.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.event.WorkSetEvent
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.model.CreateWorkSet

class WorkSetState {
    var createWorkSetLists: SnapshotStateList<CreateWorkSet> =
        emptyList<CreateWorkSet>().toMutableStateList()


    val addWorkSet: () -> Unit = {
        onWorkSetEvent(WorkSetEvent.AddWorkSet)
    }
    val updateWorkSet: (Int, CreateWorkSet) -> Unit = { index, workSet ->
        onWorkSetEvent(WorkSetEvent.UpdateWorkSet(index, workSet))
    }
    val removeWorkSet: (CreateWorkSet) -> Unit = {
        onWorkSetEvent(WorkSetEvent.RemoveWorkSet(it))
    }


    private fun onWorkSetEvent(workSetEvent: WorkSetEvent) {
        when (workSetEvent) {
            WorkSetEvent.AddWorkSet -> {
                createWorkSetLists.add(
                    if (createWorkSetLists.isEmpty()) CreateWorkSet("", "")
                    else createWorkSetLists.last()
                )
            }

            is WorkSetEvent.RemoveWorkSet -> {
                createWorkSetLists.remove(workSetEvent.createWorkSet)

            }

            is WorkSetEvent.UpdateWorkSet -> {
                createWorkSetLists[workSetEvent.index] = workSetEvent.createWorkSet
            }
        }
    }
}