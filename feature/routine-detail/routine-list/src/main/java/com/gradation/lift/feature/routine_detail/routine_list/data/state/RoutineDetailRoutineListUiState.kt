package com.gradation.lift.feature.routine_detail.routine_list.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.routine_detail.routine_list.data.model.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

fun routineDetailRoutineListUiState(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase,
    sortFilterState: StateFlow<SortFilterState>
): Flow<RoutineDetailRoutineListUiState> {
    return combine(
        getRoutineSetRoutineUseCase(),
        sortFilterState
    ) { routineSetRoutine, state ->

        when (routineSetRoutine) {
            is DataState.Fail -> {
                RoutineDetailRoutineListUiState.Fail(routineSetRoutine.message)
            }

            is DataState.Success -> {
                if (routineSetRoutine.data.isEmpty()) {
                    RoutineDetailRoutineListUiState.Empty
                } else {
                    RoutineDetailRoutineListUiState.Success(
                        routineSetRoutine.data.let { routineSetRoutineList ->
                            when (val labelFilterType = state.labelFilterType) {
                                LabelFilterType.All -> {
                                    routineSetRoutineList
                                }

                                is LabelFilterType.Label -> {
                                    routineSetRoutineList.filter {
                                        it.label.intersect(labelFilterType.labelSet).isNotEmpty()
                                    }
                                }
                            }
                        }.let { filteredRoutineSetRoutine ->
                            when (val weekdayFilterType = state.weekdayFilterType) {
                                WeekdayFilterType.All -> {
                                    filteredRoutineSetRoutine
                                }

                                is WeekdayFilterType.Weekday -> {
                                    filteredRoutineSetRoutine.filter {
                                        it.label.intersect(weekdayFilterType.weekdaySet)
                                            .isNotEmpty()
                                    }
                                }
                            }
                        }.let { filteredRoutineSetRoutine ->
                            filteredRoutineSetRoutine.filter {
                                it.name.contains(state.searchTextFilter)
                            }
                        }.let { filteredRoutineSetRoutine ->
                            when (state.sortType) {
                                SortType.Name -> filteredRoutineSetRoutine.sortedBy { it.name }
                                SortType.Count -> filteredRoutineSetRoutine.sortedByDescending { it.count }
                            }
                        }
                    )
                }
            }
        }
    }
}


sealed interface RoutineDetailRoutineListUiState {

    data class Success(val routineSetRoutineList: List<RoutineSetRoutine>) :
        RoutineDetailRoutineListUiState

    data class Fail(val message: String) : RoutineDetailRoutineListUiState
    data object Empty : RoutineDetailRoutineListUiState
    data object Loading : RoutineDetailRoutineListUiState
}