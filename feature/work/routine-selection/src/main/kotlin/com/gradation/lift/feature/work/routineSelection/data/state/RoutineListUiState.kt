package com.gradation.lift.feature.work.routineSelection.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.work.routineSelection.data.model.SortType
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal fun routineDetailRoutineListUiState(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase,
    sortFilterState: SortFilterState,
): Flow<RoutineListUiState> {

    return combine(
        getRoutineSetRoutineUseCase(),
        sortFilterState.labelFilterType,
        sortFilterState.weekdayFilterType,
        sortFilterState.searchFilterText,
        sortFilterState.sortType
    ) { routineSetRoutine, labelFilterType, weekdayFilterType, searchFilterText, sortType ->

        when (routineSetRoutine) {
            is DataState.Fail -> {
                RoutineListUiState.Fail(routineSetRoutine.message)
            }

            is DataState.Success -> {
                RoutineListUiState.Success(
                    routineSetRoutine.data.let { routineSetRoutineList ->
                        routineSetRoutineList.filter {
                            it.label.intersect(labelFilterType.labelSet).isNotEmpty() ||
                                    (it.label.isEmpty() && labelFilterType.isCheckedAllLabel())
                        }
                    }.let { filteredRoutineSetRoutine ->
                        filteredRoutineSetRoutine.filter {
                            it.weekday.intersect(weekdayFilterType.weekdaySet).isNotEmpty()
                        }
                    }.let { filteredRoutineSetRoutine ->
                        filteredRoutineSetRoutine.filter { routine ->
                            searchFilterText.isEmpty() ||
                                    routine.name.contains(searchFilterText)
                                    || routine.routine.any {
                                it.workCategory.name.contains(
                                    searchFilterText
                                )
                            }
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


internal sealed interface RoutineListUiState {

    data class Success(val routineSetRoutineList: List<RoutineSetRoutine>) : RoutineListUiState
    data class Fail(val message: String) : RoutineListUiState
    data object Loading : RoutineListUiState
}