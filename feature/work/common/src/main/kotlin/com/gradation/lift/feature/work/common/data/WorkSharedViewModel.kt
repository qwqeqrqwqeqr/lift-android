package com.gradation.lift.feature.work.common.data

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.domain.usecase.work.GetWorkUseCase
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet
import com.gradation.lift.feature.work.common.data.state.WorkRoutineInfoState
import com.gradation.lift.feature.work.common.data.state.WorkState
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkSharedViewModel @Inject constructor(
    initTimerUseCase: InitTimerUseCase,
    private val getWorkUseCase: GetWorkUseCase,
) : ViewModel() {

    val historyRoutineList: MutableStateFlow<List<CreateHistoryRoutine>> =
        MutableStateFlow(emptyList())
    val historyWorkRestTime: MutableStateFlow<WorkRestTime> = MutableStateFlow(WorkRestTime())
    val historyProgress: MutableStateFlow<Float> = MutableStateFlow(0f)

    val setHistoryRoutineList: (List<CreateHistoryRoutine>) -> Unit = {
        historyRoutineList.value = it
    }
    val setHistoryWorkRestTime: (WorkRestTime) -> Unit = {
        historyWorkRestTime.value = it
    }
    val setHistoryProgress: (Float) -> Unit = {
        historyProgress.value = it
    }
    val usedRoutineSetIdList: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())

    val workState = WorkState(initTimerUseCase, viewModelScope)

    val workRoutineInfoState: WorkRoutineInfoState = WorkRoutineInfoState()


    init {
        viewModelScope.launch {
            getWorkUseCase().collect {
                when (val result = it) {
                    is DataState.Fail -> {}
                    is DataState.Success -> {
                        workState.workRoutineList.addAll(result.data.routine.mapIndexed { index, workRoutine ->
                            WorkRoutine(
                                index,
                                workRoutine.workCategory,
                                workRoutine.workSetList.map { workSet ->
                                    WorkRoutineWorkSet(
                                        weight = workSet.weight.toString(),
                                        repetition = workSet.repetition.toString()
                                    )
                                }.toMutableStateList()
                            )
                        })
                        workState.startTimer()
                        usedRoutineSetIdList.update { result.data.usedRoutineSetIdList }
                    }
                }
            }
        }
    }

}