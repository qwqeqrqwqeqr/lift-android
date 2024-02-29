package com.gradation.lift.feature.work.common.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.feature.work.common.data.event.WorkRoutineEvent
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

class WorkState(
    private val initTimerUseCase: InitTimerUseCase,
    private val viewModelScope: CoroutineScope,
    var workRoutineList: SnapshotStateList<WorkRoutine> = emptyList<WorkRoutine>().toMutableStateList(),
    var currentWorkRoutineIndex: MutableStateFlow<Int> = MutableStateFlow(0),
    private var workRestTime: MutableStateFlow<WorkRestTime> = MutableStateFlow(WorkRestTime()),
    private var workRestFlag: MutableStateFlow<Boolean> = MutableStateFlow(true),
) {

    private lateinit var timerJob: Job


    val workTime: StateFlow<LocalTime> = workRestTime.map { it.workTime }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LocalTime(0, 0, 0)
    )

    val restTime: StateFlow<LocalTime> = workRestTime.map { it.restTime }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LocalTime(0, 0, 0)
    )

    val updateWorkFlag: (Boolean) -> Unit = { workRestFlag.value = it }

    val getWorkRoutineListSize: () -> Int = {  workRoutineList.size }
    val updateCurrentWorkRoutineIndex: (Int) -> Unit = { currentWorkRoutineIndex.value = it }

    val minusWorkRoutineIndex: () -> Unit = {
        if (currentWorkRoutineIndex.value > 0)
            currentWorkRoutineIndex.value = currentWorkRoutineIndex.value - 1
    }

    val plusWorkRoutineIndex: () -> Unit = {
        if (currentWorkRoutineIndex.value < workRoutineList.size - 1)
            currentWorkRoutineIndex.value = currentWorkRoutineIndex.value + 1
    }


    val getCurrentWorkRoutine: () -> WorkRoutine =
        { workRoutineList[currentWorkRoutineIndex.value] }

    val getPreWorkRoutine: () -> WorkRoutine? = {
        if (currentWorkRoutineIndex.value <= 0 || workRoutineList.size==0) null
        else workRoutineList[currentWorkRoutineIndex.value - 1]
    }
    val getNextWorkRoutine: () -> WorkRoutine? = {
        if (currentWorkRoutineIndex.value >= workRoutineList.size - 1 || workRoutineList.size==0) null
        else workRoutineList[currentWorkRoutineIndex.value + 1]
    }


    fun startTimer() {
        timerJob = viewModelScope.launch {
            initTimerUseCase().map {
                if (workRestFlag.value) {
                    workRestTime.update {
                        it.copy(
                            workTime = LocalTime.fromMillisecondOfDay((it.workTime.toMillisecondOfDay() + 200))
                        )
                    }
                } else {
                    workRestTime.update {
                        it.copy(
                            restTime = LocalTime.fromMillisecondOfDay((it.restTime.toMillisecondOfDay() + 200))
                        )
                    }
                }
            }.collect()
        }
    }

    val stopTime: () -> Unit = { timerJob.cancel() }


    val appendRoutine: (WorkRoutine) -> Unit = {
        onWorkRoutineEvent(WorkRoutineEvent.AppendRoutine(it))
    }

    val removeRoutine: (WorkRoutine) -> Unit = {
        onWorkRoutineEvent(WorkRoutineEvent.RemoveRoutine(it))
    }

    val addWorkSet: (Int, WorkRoutineWorkSet) -> Unit = { routineId, workRoutineWorkSet ->
        onWorkRoutineEvent(
            WorkRoutineEvent.AddWorkSet(
                routineId,
                workRoutineWorkSet
            )
        )
    }

    val removeWorkSet: (Int, WorkRoutineWorkSet) -> Unit = { routineId, workRoutineWorkSet ->
        onWorkRoutineEvent(
            WorkRoutineEvent.RemoveWorkSet(
                routineId,
                workRoutineWorkSet
            )
        )
    }
    val updateWorkSet: (Int, Int, WorkRoutineWorkSet) -> Unit =
        { routineId, workSetIndex, workRoutineWorkSet ->
            onWorkRoutineEvent(
                WorkRoutineEvent.UpdateWorkSet(
                    routineId,
                    workSetIndex,
                    workRoutineWorkSet
                )
            )
        }


    private fun onWorkRoutineEvent(workRoutineEvent: WorkRoutineEvent) {
        when (workRoutineEvent) {
            is WorkRoutineEvent.AppendRoutine -> {
                workRoutineList.add(workRoutineEvent.workRoutine)
            }

            is WorkRoutineEvent.RemoveRoutine -> {
                workRoutineList.remove(workRoutineEvent.workRoutine)
            }

            is WorkRoutineEvent.AddWorkSet -> {
                workRoutineList[workRoutineEvent.routineIndex].workSetList.add(
                    workRoutineEvent.workRoutineWorkSet
                )
            }

            is WorkRoutineEvent.RemoveWorkSet -> {
                workRoutineList[workRoutineEvent.routineIndex].workSetList.remove(
                    workRoutineEvent.workRoutineWorkSet
                )
            }

            is WorkRoutineEvent.UpdateWorkSet -> {
                workRoutineList[workRoutineEvent.routineIndex].workSetList[workRoutineEvent.workSetIndex] =
                    workRoutineEvent.workRoutineWorkSet
            }


        }
    }


}