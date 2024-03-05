package com.gradation.lift.feature.workReady.common

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.feature.workReady.common.model.WorkRoutineWorkSet
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Work.WORK_ROUTINE_SET_ID_LIST_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkReadySharedViewModel @Inject constructor(
    private val getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val workRoutineState = WorkRoutineState()

    var routineSetIdSet: MutableStateFlow<Set<Int>> =
        MutableStateFlow(
            savedStateHandle.get<String>(WORK_ROUTINE_SET_ID_LIST_KEY)
                ?.let {
                    it.split("|")
                        .filter { it != "" }
                        .map { it.toInt() }
                        .toSet()
                } ?: emptySet()
        )
    val updateRoutineSetIdSet: (Set<Int>) -> Unit = { routineSetIdSet.value = it }


    val initRoutineList: () -> Unit = {
        viewModelScope.launch {
            if (workRoutineState.currentWorkRoutine.isEmpty() && routineSetIdSet.value.isNotEmpty()) {
                getRoutineSetRoutineByRoutineSetIdUseCase(routineSetIdSet.value).map { routineSetRoutine ->
                    when (routineSetRoutine) {
                        is DataState.Fail -> emptyList()
                        is DataState.Success -> routineSetRoutine.data.flatMap { it.routine }
                            .mapIndexed { id, routine ->
                                WorkRoutine(
                                    id = id,
                                    workCategoryId = routine.workCategoryId,
                                    workCategoryName = routine.workCategoryName,
                                    workPart = routine.workPart,
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

