package com.gradation.lift.feature.workReady.common

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.feature.workReady.common.model.WorkRoutineWorkSet
import java.util.Collections

data class WorkRoutineState(
    val currentWorkRoutine: SnapshotStateList<WorkRoutine> =
        emptyList<WorkRoutine>().toMutableStateList(),
) {
    private val tempUndoWorkRoutine: SnapshotStateList<WorkRoutine> =
        emptyList<WorkRoutine>().toMutableStateList()

    val appendRoutine: (WorkRoutine) -> Unit = {
        onCurrentRoutineSetRoutineEvent(WorkRoutineEvent.AppendRoutine(it))
    }

    val removeRoutine: (WorkRoutine) -> Unit = {
        onCurrentRoutineSetRoutineEvent(WorkRoutineEvent.RemoveRoutine(it))
    }

    val removeRoutines: (List<Int>) -> Unit = {
        onCurrentRoutineSetRoutineEvent(WorkRoutineEvent.RemoveRoutines(it))
    }

    val moveRoutine: (Int, Int) -> Unit = { from, to ->
        onCurrentRoutineSetRoutineEvent(WorkRoutineEvent.MoveRoutine(from, to))
    }
    val undo: () -> Unit = {
        onCurrentRoutineSetRoutineEvent(WorkRoutineEvent.Undo)
    }
    val clear: () -> Unit = {
        onCurrentRoutineSetRoutineEvent(WorkRoutineEvent.Clear)
    }




    val addWorkSet: (Int, WorkRoutineWorkSet) -> Unit = { routineIndex, workRoutineWorkSet ->
        onCurrentRoutineSetRoutineEvent(
            WorkRoutineEvent.AddWorkSet(
                routineIndex,
                workRoutineWorkSet
            )
        )
    }

    val removeWorkSet: (Int, WorkRoutineWorkSet) -> Unit = { routineIndex, workRoutineWorkSet ->
        onCurrentRoutineSetRoutineEvent(
            WorkRoutineEvent.RemoveWorkSet(
                routineIndex,
                workRoutineWorkSet
            )
        )
    }
    val updateWorkSet: (Int, Int, WorkRoutineWorkSet) -> Unit =
        { routineIndex, workSetIndex, workRoutineWorkSet ->
            onCurrentRoutineSetRoutineEvent(
                WorkRoutineEvent.UpdateWorkSet(
                    routineIndex,
                    workSetIndex,
                    workRoutineWorkSet
                )
            )
        }


    private fun onCurrentRoutineSetRoutineEvent(workRoutineEvent: WorkRoutineEvent) {
        when (workRoutineEvent) {
            is WorkRoutineEvent.AppendRoutine -> {
                currentWorkRoutine.add(workRoutineEvent.workRoutine)
            }

            is WorkRoutineEvent.RemoveRoutine -> {
                tempUndoWorkRoutine.clear()
                currentWorkRoutine.remove(workRoutineEvent.workRoutine)
                tempUndoWorkRoutine.add(workRoutineEvent.workRoutine)
            }
            is WorkRoutineEvent.RemoveRoutines ->{
                tempUndoWorkRoutine.clear()
                workRoutineEvent.idList.mapNotNull { id -> currentWorkRoutine.find { it.id == id } }
                    .also { routine ->
                    currentWorkRoutine.removeAll(routine)
                    tempUndoWorkRoutine.addAll(routine)
                }
            }
            WorkRoutineEvent.Undo -> {
                currentWorkRoutine.addAll(tempUndoWorkRoutine)
                tempUndoWorkRoutine.clear()
            }

            is WorkRoutineEvent.MoveRoutine -> {
                Collections.swap(
                    currentWorkRoutine,
                    workRoutineEvent.from,
                    workRoutineEvent.to
                )
            }

            WorkRoutineEvent.Clear -> currentWorkRoutine.clear()

            is WorkRoutineEvent.AddWorkSet -> {
                currentWorkRoutine[workRoutineEvent.routineIndex].workSetList.add(workRoutineEvent.workRoutineWorkSet)
            }

            is WorkRoutineEvent.RemoveWorkSet -> {
                currentWorkRoutine[workRoutineEvent.routineIndex].workSetList.remove(
                    workRoutineEvent.workRoutineWorkSet
                )
            }

            is WorkRoutineEvent.UpdateWorkSet -> {
                currentWorkRoutine[workRoutineEvent.routineIndex].workSetList[workRoutineEvent.workSetIndex] =
                    workRoutineEvent.workRoutineWorkSet
            }


        }
    }
}
