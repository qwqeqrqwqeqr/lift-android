package com.gradation.lift.feature.ready_work.change_order.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.domain.usecase.work.CreateWorkUseCase
import com.gradation.lift.model.work.Work
import com.gradation.lift.model.work.WorkRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ReadyWorkChangeOrderViewModel @Inject constructor(
    getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
    private val createWorkUseCase: CreateWorkUseCase,
) : ViewModel() {


    private val routineSetIdList = MutableStateFlow(emptySet<Int>())


    val routineSetRoutine = routineSetIdList.flatMapLatest {
        getRoutineSetRoutineByRoutineSetIdUseCase(it).map { result ->
            when (result) {
                is DataState.Fail -> RoutineSetRoutineUiState.Fail(message = result.message)
                is DataState.Success -> {
                    if (result.data.isEmpty()) {
                        RoutineSetRoutineUiState.Empty
                    } else {
                        RoutineSetRoutineUiState.Success(result.data)
                    }
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RoutineSetRoutineUiState.Loading
    )

    fun updateSelectedRoutineSetIdList(SelectedRoutineSetIdList: Set<Int>?) {
        SelectedRoutineSetIdList?.let { selectedRoutineSetIdList ->
            routineSetIdList.update { list ->
                list.plus(selectedRoutineSetIdList)
            }
        }
    }

    fun deleteRoutineSetIdList(): (Int) -> Unit = { id ->
        if (routineSetIdList.value.size > 1) {
            routineSetIdList.update { it.minusElement(id) }
        }
    }

    fun createWork() {
        viewModelScope.launch {
            when (val result = routineSetRoutine.value) {
                RoutineSetRoutineUiState.Empty -> TODO()
                is RoutineSetRoutineUiState.Fail -> TODO()
                RoutineSetRoutineUiState.Loading -> TODO()
                is RoutineSetRoutineUiState.Success -> {
                 //TODO create routine 추가
                }
            }
        }
    }
}