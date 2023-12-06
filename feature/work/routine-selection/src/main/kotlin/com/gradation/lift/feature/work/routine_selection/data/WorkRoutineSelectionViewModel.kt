package com.gradation.lift.feature.work.routine_selection.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetCurrentWeekUseCase
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.feature.work.routine_selection.data.state.DateState
import com.gradation.lift.feature.work.routine_selection.data.state.OpenedRoutineState
import com.gradation.lift.feature.work.routine_selection.data.state.RoutineSetRoutineSelectionUiState
import com.gradation.lift.feature.work.routine_selection.data.state.SelectedRoutineSetState
import com.gradation.lift.feature.work.work.data.model.RoutineSelection
import com.gradation.lift.feature.work.work.data.model.RoutineSetRoutineSelection
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.toWeekday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [WorkRoutineSelectionViewModel]
 * @property selectedRoutineSetState 뷰와 상호작용을 통해 선택된 루틴들의 정보 상태
 * @property openedRoutineState 뷰와 상호작용을 통해 상세정보가 열린 루틴들의 정보 상태
 * @property dateState 날짜와 관련된 상태
 * @property routineSetRoutineState 상단 3개의 상태를 조합하여 만든 루틴 세트 상태
 *
 * @constructor getThisWeekUseCase 이번주에 대한 정보를 불러옴 (날짜, 요일)
 * @constructor getRoutineSetRoutineByWeekdayUseCase  요일을 통해 루틴세트를 불러옴
 * @constructor getRoutineSetRoutineByRoutineSetIdUseCase 루틴세트 아이디를 통해 루틴세트를 불러옴
 * @constructor getTodayUseCase 현재 날짜 시간 정보를 불러옴
 * @since 2023-08-22 12:33:46
 */
@HiltViewModel

class WorkRoutineSelectionViewModel @Inject constructor(
    private val getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase,
    getCurrentWeekUseCase: GetCurrentWeekUseCase,
    getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
    getTodayUseCase: GetTodayUseCase,
) : ViewModel() {


    var selectedRoutineSetState = SelectedRoutineSetState(
        getRoutineSetRoutineByRoutineSetIdUseCase = getRoutineSetRoutineByRoutineSetIdUseCase,
        viewModelScope = viewModelScope
    )
    val openedRoutineState = OpenedRoutineState()

    val dateState = DateState(
        getTodayUseCase = getTodayUseCase,
        getCurrentWeekUseCase = getCurrentWeekUseCase,
        viewModelScope = viewModelScope
    )


    val routineSetRoutineState: StateFlow<RoutineSetRoutineSelectionUiState> =
        combine(
            openedRoutineState.openedRoutineIdList,
            selectedRoutineSetState.selectedRoutineSetList,
            getRoutineSetRoutineByWeekdayUseCase(setOf(Weekday.Monday()))
        ) { openedRoutineIdList, selectedRoutineSetList, getRoutineSetRoutineByWeekdayResult ->
            when (getRoutineSetRoutineByWeekdayResult) {
                is DataState.Fail -> RoutineSetRoutineSelectionUiState.Fail(message = getRoutineSetRoutineByWeekdayResult.message)
                is DataState.Success -> {
                    if (getRoutineSetRoutineByWeekdayResult.data.isEmpty()) {
                        RoutineSetRoutineSelectionUiState.Empty
                    } else {
                        RoutineSetRoutineSelectionUiState.Success(
                            getRoutineSetRoutineByWeekdayResult.data.map { routineSetRoutine ->
                                RoutineSetRoutineSelection(
                                    id = routineSetRoutine.id,
                                    name = routineSetRoutine.name,
                                    description = routineSetRoutine.description,
                                    selected = (selectedRoutineSetList.map { it -> it.id }
                                        .contains(routineSetRoutine.id)),
                                    routine = routineSetRoutine.routine.map { routine ->
                                        RoutineSelection(
                                            routine = routine,
                                            opened = (openedRoutineIdList.contains(routine.id))
                                        )
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineSetRoutineSelectionUiState.Loading
        )


}