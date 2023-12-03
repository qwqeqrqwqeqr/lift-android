package com.gradation.lift.feature.routine_detail.routine_list.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal fun routineDetailRoutineListUiState(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase,
    sortFilterState: SortFilterState,
): Flow<RoutineDetailRoutineListUiState> {

    return combine(
        getRoutineSetRoutineUseCase(),
        sortFilterState.labelFilterType,
        sortFilterState.weekdayFilterType,
        sortFilterState.searchFilterText,
        sortFilterState.sortType
    ) { routineSetRoutine, labelFilterType, weekdayFilterType, searchFilterText, sortType ->

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
                            routineSetRoutineList.filter {
                                it.label.intersect(labelFilterType.labelSet).isNotEmpty()
                            }
                        }.let { filteredRoutineSetRoutine ->
                            filteredRoutineSetRoutine.filter {
                                it.weekday.intersect(weekdayFilterType.weekdaySet).isNotEmpty()
                            }
                        }.let { filteredRoutineSetRoutine ->
                            filteredRoutineSetRoutine.filter { routine ->
                                searchFilterText.isEmpty() ||
                                        routine.name.contains(searchFilterText)
                            }
                        }.let { filteredRoutineSetRoutine ->
                            when (sortType) {
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