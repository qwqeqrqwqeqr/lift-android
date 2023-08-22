package com.gradation.lift.feature.work.routine_selection.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [SelectedRoutineSetState]
 * @property selectedRoutineSetList 현재 '선택 상태'인 루틴세트 리스트
 * @property selectedRoutineCount 현재 선택된 루틴세트의 개수
 * @since 2023-08-22 12:39:37
 */
class SelectedRoutineSetState @Inject constructor(
    private val getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
    private val viewModelScope: CoroutineScope,
) {

    val selectedRoutineSetList: MutableStateFlow<List<RoutineSetRoutine>> =
        MutableStateFlow(emptyList())

    val selectedRoutineCount: StateFlow<Int> = selectedRoutineSetList.map { it -> it.size }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

    fun appendBySelectedRoutineSetRoutineId(): (Int?) -> Unit = {
        it?.let { id ->
            viewModelScope.launch {
                getRoutineSetRoutineByRoutineSetIdUseCase(setOf(id)).collect { routineSetResult ->
                    when (routineSetResult) {
                        is DataState.Fail -> {}
                        is DataState.Success -> {
                            selectedRoutineSetList.update { list ->
                                list.plus(routineSetResult.data)
                            }
                        }
                    }
                }
            }
        }
    }


    fun updateSelectedRoutineSetList(): (RoutineSetRoutine, Boolean) -> Unit =
        { routineSetRoutine, checked ->
            if (checked) {
                selectedRoutineSetList.update { it.plusElement(routineSetRoutine) }
            } else {
                selectedRoutineSetList.update { it.minusElement(routineSetRoutine) }
            }
        }
}