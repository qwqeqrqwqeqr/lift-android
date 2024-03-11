package com.gradation.lift.feature.workReady.common

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Constants.SEPARATOR
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutine
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutineWorkSet
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
                    it.split(SEPARATOR)
                        .filter { it.isNotEmpty() }
                        .map { it.toInt() }
                        .toSet()
                } ?: emptySet()
        )

    val updateRoutineSetIdSet: (Set<Int>) -> Unit = {
        routineSetIdSet.value = it
        initRoutineList()
    }


    val initRoutineList: () -> Unit = {
        viewModelScope.launch {
            getRoutineSetRoutineByRoutineSetIdUseCase(routineSetIdSet.value).map { routineSetRoutine ->
                when (routineSetRoutine) {
                    is DataState.Fail -> emptyList()
                    is DataState.Success -> routineSetRoutine.data.flatMap { it.routine }
                        .mapIndexed { id, routine ->
                            WorkReadyRoutine(
                                id = id,
                                workCategoryId = routine.workCategoryId,
                                workCategoryName = routine.workCategoryName,
                                workPart = routine.workPart,
                                workSetList = routine.workSetList.map {
                                    WorkReadyRoutineWorkSet(
                                        weight = it.weight.toString(),
                                        repetition = it.repetition.toString()
                                    )
                                }.toMutableStateList()
                            )
                        }
                }
            }.collect { workRoutineList ->
                workRoutineState.clear()
                workRoutineList.forEach {
                    workRoutineState.appendRoutine(it)
                }
            }
        }
    }

    init {
        initRoutineList()
    }
}

