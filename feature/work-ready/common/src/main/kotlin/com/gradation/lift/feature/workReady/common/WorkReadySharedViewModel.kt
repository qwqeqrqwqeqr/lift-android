package com.gradation.lift.feature.workReady.common

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.feature.workReady.common.model.WorkRoutineWorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkReadySharedViewModel @Inject constructor(
    private val getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
) : ViewModel() {

    val workRoutineState = WorkRoutineState()

    val initRoutineList: (Set<Int>) -> Unit = {
        viewModelScope.launch {
            if (workRoutineState.currentWorkRoutine.isEmpty() && it.isNotEmpty()) {
                getRoutineSetRoutineByRoutineSetIdUseCase(it).map { routineSetRoutine ->
                    when (routineSetRoutine) {
                        is DataState.Fail -> emptyList()
                        is DataState.Success -> routineSetRoutine.data.flatMap { it.routine }
                            .mapIndexed { id, routine ->
                                WorkRoutine(
                                    id = id,
                                    workCategory = routine.workCategory,
                                    workSetList = routine.workSetList.map {
                                        WorkRoutineWorkSet(
                                            weight = it.weight.toString(),
                                            repetition = it.repetition.toString()
                                        )
                                    }.toMutableStateList()
                                )
                            }
                    }
                }.collect { workRoutineList ->
                    workRoutineList.forEach {
                        workRoutineState.appendRoutine(it)
                    }
                }
            }
        }
    }
}

