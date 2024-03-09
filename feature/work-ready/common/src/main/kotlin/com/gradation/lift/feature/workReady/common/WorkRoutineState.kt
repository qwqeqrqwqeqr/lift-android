package com.gradation.lift.feature.workReady.common

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutine
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutineWorkSet
import java.util.Collections

data class WorkRoutineState(
    val currentWorkReadyRoutineList: SnapshotStateList<WorkReadyRoutine> =
        emptyList<WorkReadyRoutine>().toMutableStateList(),
) {
    private val tempUndoWorkReadyRoutines: SnapshotStateList<WorkReadyRoutine> =
        emptyList<WorkReadyRoutine>().toMutableStateList()

    val appendRoutine: (WorkReadyRoutine) -> Unit = {
        onCurrentRoutineSetRoutineEvent(WorkRoutineEvent.AppendRoutine(it))
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


    val addWorkSet: (Int, WorkReadyRoutineWorkSet) -> Unit = { routineIndex, workRoutineWorkSet ->
        onCurrentRoutineSetRoutineEvent(
            WorkRoutineEvent.AddWorkSet(
                routineIndex,
                workRoutineWorkSet
            )
        )
    }

    val removeWorkSet: (Int, WorkReadyRoutineWorkSet) -> Unit =
        { routineIndex, workRoutineWorkSet ->
            onCurrentRoutineSetRoutineEvent(
                WorkRoutineEvent.RemoveWorkSet(
                    routineIndex,
                    workRoutineWorkSet
                )
            )
        }
    val updateWorkSet: (Int, Int, WorkReadyRoutineWorkSet) -> Unit =
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
                currentWorkReadyRoutineList.add(workRoutineEvent.workReadyRoutine)
            }

            is WorkRoutineEvent.RemoveRoutine -> {
                tempUndoWorkReadyRoutines.clear()
                currentWorkReadyRoutineList.remove(workRoutineEvent.workReadyRoutine)
                tempUndoWorkReadyRoutines.add(workRoutineEvent.workReadyRoutine)
            }

            is WorkRoutineEvent.RemoveRoutines -> {
                tempUndoWorkReadyRoutines.clear()
                workRoutineEvent.idList.mapNotNull { id -> currentWorkReadyRoutineList.find { it.id == id } }
                    .also { routine ->
                        currentWorkReadyRoutineList.removeAll(routine)
                        tempUndoWorkReadyRoutines.addAll(routine)
                    }
            }

            WorkRoutineEvent.Undo -> {
                currentWorkReadyRoutineList.addAll(tempUndoWorkReadyRoutines)
                tempUndoWorkReadyRoutines.clear()
            }

            is WorkRoutineEvent.MoveRoutine -> {
                Collections.swap(
                    currentWorkReadyRoutineList,
                    workRoutineEvent.from,
                    workRoutineEvent.to
                )
            }

            WorkRoutineEvent.Clear -> currentWorkReadyRoutineList.clear()

            is WorkRoutineEvent.AddWorkSet -> {
                currentWorkReadyRoutineList[workRoutineEvent.routineIndex].workSetList.add(
                    workRoutineEvent.workReadyRoutineWorkSet
                )
            }

            is WorkRoutineEvent.RemoveWorkSet -> {
                currentWorkReadyRoutineList[workRoutineEvent.routineIndex].workSetList.remove(
                    workRoutineEvent.workReadyRoutineWorkSet
                )
            }

            is WorkRoutineEvent.UpdateWorkSet -> {
                currentWorkReadyRoutineList[workRoutineEvent.routineIndex].workSetList[workRoutineEvent.workSetIndex] =
                    workRoutineEvent.workReadyRoutineWorkSet
            }


        }
    }
}
