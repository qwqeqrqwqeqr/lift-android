package com.gradation.lift.feature.work.work.data.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.domain.usecase.work.GetWorkUseCase
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet
import com.gradation.lift.feature.work.work.data.state.WorkRoutineInfoState
import com.gradation.lift.feature.work.work.data.state.WorkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [WorkViewModel]
 */
@HiltViewModel
class WorkViewModel @Inject constructor(
    initTimerUseCase: InitTimerUseCase,
    private val getWorkUseCase: GetWorkUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            getWorkUseCase().collect {
                when (it) {
                    is DataState.Fail -> {}
                    is DataState.Success -> {
                        workState.workRoutineList.addAll(it.data.routine.mapIndexed { index, workRoutine ->
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
                    }
                }
            }
        }
    }

    val workState = WorkState(initTimerUseCase, viewModelScope)
    val workRoutineInfoState = WorkRoutineInfoState()
}





