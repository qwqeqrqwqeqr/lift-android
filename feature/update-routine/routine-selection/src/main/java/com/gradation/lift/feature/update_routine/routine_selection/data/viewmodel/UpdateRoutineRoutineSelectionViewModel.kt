package com.gradation.lift.feature.update_routine.routine_selection.data.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetCurrentWeekUseCase
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.feature.update_routine.routine_selection.data.model.RoutineSelection
import com.gradation.lift.feature.update_routine.routine_selection.data.model.RoutineSetRoutineSelection
import com.gradation.lift.feature.update_routine.routine_selection.data.state.DateState
import com.gradation.lift.feature.update_routine.routine_selection.data.state.OpenedRoutineState
import com.gradation.lift.feature.update_routine.routine_selection.data.state.RoutineSetRoutineSelectionUiState
import com.gradation.lift.model.model.date.toWeekday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [UpdateRoutineRoutineSelectionViewModel]
 * @constructor getRoutineSetRoutineByWeekdayUseCase 요일을 통해 루틴세트를 불러옴
 * @property openedRoutineState 뷰와 상호작용을 통해 상세정보가 열린 루틴들의 정보 상태
 * @property dateState 날짜와 관련된 상태
 * @property routineSetRoutineState 상단 3개의 상태를 조합 하여 만든 루틴 세트 상태
 * @since 2023-09-06 21:03:44
 */
@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class UpdateRoutineRoutineSelectionViewModel @Inject constructor(
    private val getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase,
    getCurrentWeekUseCase: GetCurrentWeekUseCase,
    getTodayUseCase: GetTodayUseCase,
) : ViewModel() {



    val openedRoutineState = OpenedRoutineState()

    val dateState = DateState(
        getTodayUseCase = getTodayUseCase,
        getCurrentWeekUseCase = getCurrentWeekUseCase,
        viewModelScope = viewModelScope
    )


    @OptIn(ExperimentalCoroutinesApi::class)
    val routineSetRoutineState: StateFlow<RoutineSetRoutineSelectionUiState> =
        dateState.currentDate.flatMapLatest {
            combine(
                openedRoutineState.openedRoutineIdList,
                getRoutineSetRoutineByWeekdayUseCase(it.toWeekday())
            ) { openedRoutineIdList, getRoutineSetRoutineByWeekdayResult ->
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
                                        weekday = routineSetRoutine.weekday,
                                        picture = routineSetRoutine.picture,
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
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineSetRoutineSelectionUiState.Loading
        )


}