package com.gradation.lift.feature.work.work.data.state

import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.model.WorkRoutine
import com.gradation.lift.feature.work.work.data.model.WorkRoutineIdInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

class WorkState(
    private val initTimerUseCase: InitTimerUseCase,
    private val viewModelScope: CoroutineScope,
) {

    private lateinit var timerJob: Job


    var currentWorkRoutineList: SnapshotStateList<WorkRoutine> =
        emptyList<WorkRoutine>().toMutableStateList()
    private val currentWorkRoutineListNumber: MutableStateFlow<Int> = MutableStateFlow(1)

    private var checkWorkSetList: SnapshotStateList<WorkRoutineIdInfo> =
        emptyList<WorkRoutineIdInfo>().toMutableStateList()


    private val workRestTime: MutableStateFlow<WorkRestTime> = MutableStateFlow(WorkRestTime())
    private val isWorkFlag: MutableStateFlow<Boolean> = MutableStateFlow(true)

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

    private val currentWorkRoutineListFlow: Flow<List<WorkRoutine>> =
        snapshotFlow { currentWorkRoutineList.toList() }
    private val checkWorkSetListFlow: Flow<List<WorkRoutineIdInfo>> =
        snapshotFlow { checkWorkSetList.toList() }


    val currentWork: StateFlow<WorkRoutine?> = combine(
        currentWorkRoutineListFlow,
        currentWorkRoutineListNumber
    ) { currentWorkRoutineList, currentWorkRoutineListNumber ->
        currentWorkRoutineList.find { currentWorkRoutineListNumber == it.key }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
    val preWork: StateFlow<WorkRoutine?> = combine(
        currentWorkRoutineListFlow,
        currentWorkRoutineListNumber
    ) { currentWorkRoutineList, currentWorkRoutineListNumber ->
        currentWorkRoutineList.find { currentWorkRoutineListNumber - 1 == it.key }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextWork: StateFlow<WorkRoutine?> = combine(
        currentWorkRoutineListFlow,
        currentWorkRoutineListNumber
    ) { currentWorkRoutineList, currentWorkRoutineListNumber ->
        currentWorkRoutineList.find { currentWorkRoutineListNumber + 1 == it.key }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )


    val updateWorkFlag: (Boolean) -> Unit = { isWorkFlag.value = it }
    val stopTime: () -> Unit = { timerJob.cancel() }
    val checkWorkSet: (WorkRoutineIdInfo) -> Unit = { checkWorkSetList.add(it) }
    val uncheckWorkSet: (WorkRoutineIdInfo) -> Unit = { checkWorkSetList.remove(it) }
    val isChecked: (Int, Int) -> Boolean =
        { number, set -> checkWorkSetList.any { it.workRoutineNumber == number && it.workSetNumber == set } }

    val isAllChecked: (WorkRoutine) -> Boolean = { workRoutine ->
        checkWorkSetList.count { it.workRoutineNumber == workRoutine.key } == workRoutine.workSetList.size
    }

    val workProgress: StateFlow<Int> = combine(
        currentWorkRoutineListFlow,
        checkWorkSetListFlow
    ) { currentWorkRoutineListFlow, checkWorkSetListFlow ->
        (
                (checkWorkSetListFlow.count().toFloat() /
                        currentWorkRoutineListFlow.flatMap { it.workSetList }.count().toFloat()
                        ) * 100).toInt()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

    val next: () -> Unit =
        { currentWorkRoutineListNumber.value = currentWorkRoutineListNumber.value + 1 }
    val pre: () -> Unit =
        { currentWorkRoutineListNumber.value = currentWorkRoutineListNumber.value - 1 }


    val removeWorkRoutineWorkSet: (Int, Int,WorkRoutineIdInfo) -> Unit = { workRoutineIndex, workSetIndex,workRoutineIdInfo ->
        checkWorkSetList.remove(workRoutineIdInfo)

        currentWorkRoutineList[workRoutineIndex].workSetList.removeAt(workSetIndex)
    }


    fun startTimer() {
        timerJob = viewModelScope.launch {
            initTimerUseCase().map {
                if (isWorkFlag.value) {
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

    companion object {
        const val MIN_PROGRESS = 0
        const val MAX_PROGRESS = 100
    }
}