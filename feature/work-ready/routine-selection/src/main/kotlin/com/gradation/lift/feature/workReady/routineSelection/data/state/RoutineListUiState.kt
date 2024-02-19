package com.gradation.lift.feature.workReady.routineSelection.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.combine
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRecentUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.workReady.routineSelection.data.model.RecentUsedRoutineSetRoutine
import com.gradation.lift.feature.workReady.routineSelection.data.model.SortType
import kotlinx.coroutines.flow.Flow

internal fun routineDetailRoutineListUiState(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase,
    getRoutineSetRoutineByRecentUseCase: GetRoutineSetRoutineByRecentUseCase,
    sortFilterState: SortFilterState,
): Flow<RoutineListUiState> {

    return combine(
        getRoutineSetRoutineUseCase(),
        getRoutineSetRoutineByRecentUseCase(),
        sortFilterState.labelFilterType,
        sortFilterState.weekdayFilterType,
        sortFilterState.searchFilterText,
        sortFilterState.sortType
    ) { routineSetRoutine, recentUsed, labelFilterType, weekdayFilterType, searchFilterText, sortType ->

        when (routineSetRoutine) {
            is DataState.Fail -> {
                RoutineListUiState.Fail(routineSetRoutine.message)
            }

            is DataState.Success -> {
                when (recentUsed) {
                    is DataState.Fail -> RoutineListUiState.Fail(recentUsed.message)
                    is DataState.Success -> {
                        RoutineListUiState.Success(
                            routineListState = RoutineListState(routineSetRoutine.data.let { routineSetRoutineList ->
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
                            }.map {
                                RecentUsedRoutineSetRoutine(
                                    routineSetRoutine = it,
                                    recentUsed = recentUsed.data.contains(it)
                                )
                            })
                        )
                    }
                }


            }
        }
    }
}


internal sealed interface RoutineListUiState {

    data class Success(val routineListState: RoutineListState) : RoutineListUiState
    data class Fail(val message: String) : RoutineListUiState
    data object Loading : RoutineListUiState
}