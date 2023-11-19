package com.gradation.lift.feature.routine_detail.routine_list.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.routine_detail.routine_list.data.event.RoutineListInfoEvent
import com.gradation.lift.feature.routine_detail.routine_list.data.event.SortFilterEvent
import com.gradation.lift.feature.routine_detail.routine_list.data.model.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineDetailRoutineListUiState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListInfoState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.routineDetailRoutineListUiState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.Weekday.Companion.WEEKDAY_SIZE
import com.gradation.lift.model.model.routine.Label
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RoutineDetailRoutineListViewModel @Inject constructor(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase
) : ViewModel() {


    private val _sortFilterState: MutableStateFlow<SortFilterState> =
        MutableStateFlow(SortFilterState.Initial())
    val sortFilterState: StateFlow<SortFilterState> = _sortFilterState.asStateFlow()


    private val _routineListInfoState: MutableStateFlow<RoutineListInfoState> =
        MutableStateFlow(RoutineListInfoState.Initial())
    val routineListInfoState: StateFlow<RoutineListInfoState> = _routineListInfoState.asStateFlow()


    val routineSetRoutineList: StateFlow<RoutineDetailRoutineListUiState> =
        routineDetailRoutineListUiState(
            getRoutineSetRoutineUseCase, sortFilterState
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineDetailRoutineListUiState.Loading
        )

    fun openRoutineInfo(): (Pair<Int, Int>) -> Unit = {
        onRoutineListInfoEvent(RoutineListInfoEvent.OpenRoutineInfo(it))
    }

    fun closeRoutineInfo(): (Pair<Int, Int>) -> Unit = {
        onRoutineListInfoEvent(RoutineListInfoEvent.CloseRoutineInfo(it))
    }

    fun updateAllWeekdayFilter(): () -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateAllWeekdayFilter)
    }

    fun updateWeekdayFilter(): (Set<Weekday>) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateWeekdayFilter(it))
    }

    fun updateAllLabelFilter(): () -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateAllLabelFilter)
    }

    fun updateLabelFilter(): (Set<Label>) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateLabelFilter(it))
    }

    fun updateSortType(): (SortType) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateSortType(it))
    }

    fun updateSearchText(): (String) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateSearchText(it))
    }



    private fun onRoutineListInfoEvent(routineListInfoEvent: RoutineListInfoEvent) {
        when (routineListInfoEvent) {
            is RoutineListInfoEvent.CloseRoutineInfo -> {
                _routineListInfoState.value = RoutineListInfoState.Update(
                    openedRoutineList = _routineListInfoState.value.openedRoutineList.minus(
                        routineListInfoEvent.id
                    )
                )
            }

            is RoutineListInfoEvent.OpenRoutineInfo -> {
                _routineListInfoState.value = RoutineListInfoState.Update(
                    openedRoutineList = _routineListInfoState.value.openedRoutineList.plus(
                        routineListInfoEvent.id
                    )
                )
            }
        }
    }

    private fun onSortFilterEvent(sortFilterEvent: SortFilterEvent) {
        when (sortFilterEvent) {
            SortFilterEvent.UpdateAllLabelFilter -> {
                _sortFilterState.value = SortFilterState.Update(
                    labelFilterType = LabelFilterType.All,
                    weekdayFilterType = sortFilterState.value.weekdayFilterType,
                    searchTextFilter = sortFilterState.value.searchTextFilter,
                    sortType = sortFilterState.value.sortType
                )
            }

            SortFilterEvent.UpdateAllWeekdayFilter -> {
                _sortFilterState.value = SortFilterState.Update(
                    labelFilterType = sortFilterState.value.labelFilterType,
                    weekdayFilterType = WeekdayFilterType.All,
                    searchTextFilter = sortFilterState.value.searchTextFilter,
                    sortType = sortFilterState.value.sortType
                )
            }

            is SortFilterEvent.UpdateLabelFilter -> {
                _sortFilterState.value = SortFilterState.Update(
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
                _sortFilterState.value = SortFilterState.Update(
                    labelFilterType = sortFilterState.value.labelFilterType,
                    weekdayFilterType = sortFilterState.value.weekdayFilterType,
                    searchTextFilter = sortFilterEvent.searchText,
                    sortType = sortFilterState.value.sortType
                )
            }

            is SortFilterEvent.UpdateSortType -> {
                _sortFilterState.value = SortFilterState.Update(
                    labelFilterType = sortFilterState.value.labelFilterType,
                    weekdayFilterType = sortFilterState.value.weekdayFilterType,
                    searchTextFilter = sortFilterState.value.searchTextFilter,
                    sortType = sortFilterEvent.sortType
                )
            }

            is SortFilterEvent.UpdateWeekdayFilter -> {
                _sortFilterState.value = SortFilterState.Update(
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


