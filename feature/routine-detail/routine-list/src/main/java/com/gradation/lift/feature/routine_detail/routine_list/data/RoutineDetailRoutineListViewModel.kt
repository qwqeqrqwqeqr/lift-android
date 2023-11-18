package com.gradation.lift.feature.routine_detail.routine_list.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.routine_detail.routine_list.data.event.SortFilterEvent
import com.gradation.lift.feature.routine_detail.routine_list.data.state.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineDetailRoutineListUiState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.WeekdayFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.routineDetailRoutineListUiState
import com.gradation.lift.model.model.date.Weekday.Companion.WEEKDAY_SIZE
import com.gradation.lift.model.model.routine.Label
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RoutineDetailRoutineListViewModel @Inject constructor(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase
) : ViewModel() {


    private val sortFilterState: MutableStateFlow<SortFilterState> =
        MutableStateFlow(SortFilterState.Initial())


    val routineSetRoutineList = routineDetailRoutineListUiState(
        getRoutineSetRoutineUseCase, sortFilterState
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RoutineDetailRoutineListUiState.Loading
    )

    fun onSortFilterEvent(sortFilterEvent: SortFilterEvent) {
        when (sortFilterEvent) {
            SortFilterEvent.UpdateAllLabelFilter -> {
                sortFilterState.value = SortFilterState.Update(
                    labelFilterType = LabelFilterType.All,
                    weekdayFilterType = sortFilterState.value.weekdayFilterType,
                    searchTextFilter = sortFilterState.value.searchTextFilter,
                    sortType = sortFilterState.value.sortType
                )
            }

            SortFilterEvent.UpdateAllWeekdayFilter -> {
                sortFilterState.value = SortFilterState.Update(
                    labelFilterType = sortFilterState.value.labelFilterType,
                    weekdayFilterType = WeekdayFilterType.All,
                    searchTextFilter = sortFilterState.value.searchTextFilter,
                    sortType = sortFilterState.value.sortType
                )
            }

            is SortFilterEvent.UpdateLabelFilter -> {
                sortFilterState.value = SortFilterState.Update(
                    labelFilterType =
                    if (sortFilterEvent.labelSet.size == Label.entries.size)
                        LabelFilterType.All
                    else LabelFilterType.Label(labelSet = sortFilterEvent.labelSet),
                    weekdayFilterType = sortFilterState.value.weekdayFilterType,
                    searchTextFilter = sortFilterState.value.searchTextFilter,
                    sortType = sortFilterState.value.sortType
                )
            }

            is SortFilterEvent.UpdateSearchText -> {
                sortFilterState.value = SortFilterState.Update(
                    labelFilterType = sortFilterState.value.labelFilterType,
                    weekdayFilterType = sortFilterState.value.weekdayFilterType,
                    searchTextFilter = sortFilterEvent.searchText,
                    sortType = sortFilterState.value.sortType
                )
            }

            is SortFilterEvent.UpdateSortType -> {
                sortFilterState.value = SortFilterState.Update(
                    labelFilterType = sortFilterState.value.labelFilterType,
                    weekdayFilterType = sortFilterState.value.weekdayFilterType,
                    searchTextFilter = sortFilterState.value.searchTextFilter,
                    sortType = sortFilterEvent.sortType
                )
            }

            is SortFilterEvent.UpdateWeekdayFilter -> {
                sortFilterState.value = SortFilterState.Update(
                    labelFilterType = sortFilterState.value.labelFilterType,
                    weekdayFilterType = if (sortFilterEvent.weekdaySet.size == WEEKDAY_SIZE)
                        WeekdayFilterType.All
                    else WeekdayFilterType.Weekday(weekdaySet = sortFilterEvent.weekdaySet),
                    searchTextFilter = sortFilterState.value.searchTextFilter,
                    sortType = sortFilterState.value.sortType
                )
            }
        }
    }
}


