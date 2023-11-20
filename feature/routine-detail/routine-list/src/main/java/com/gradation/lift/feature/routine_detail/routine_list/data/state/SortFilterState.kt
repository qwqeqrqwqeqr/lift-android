package com.gradation.lift.feature.routine_detail.routine_list.data.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.gradation.lift.feature.routine_detail.routine_list.data.event.SortFilterEvent
import com.gradation.lift.feature.routine_detail.routine_list.data.model.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.Weekday.Companion.WEEKDAY_SIZE
import com.gradation.lift.model.model.routine.Label

/**
 * [SortFilterState]
 * 루틴을 조회할 때에 대한 정렬 필터를 모아둔 상태필
 * @param labelFilterType 라벨 필터
 * @param weekdayFilterType 요일 필터
 * @param searchTextFilter 검색어 필터
 * @param sortType 정렬 방식
 * @since 2023-11-18 17:25:09
 */
data class SortFilterState(
    val labelFilterType: MutableState<LabelFilterType> = mutableStateOf(LabelFilterType.All),
    val weekdayFilterType: MutableState<WeekdayFilterType> = mutableStateOf(WeekdayFilterType.All),
    val searchTextFilter: MutableState<String> = mutableStateOf(""),
    val sortType: MutableState<SortType> = mutableStateOf(SortType.Name)
) {
    val updateAllWeekdayFilter: () -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateAllWeekdayFilter)
    }

    val updateWeekdayFilter: (Set<Weekday>) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateWeekdayFilter(it))
    }

    val updateAllLabelFilter: () -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateAllLabelFilter)
    }

    val updateLabelFilter: (Set<Label>) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateLabelFilter(it))
    }

    val updateSortType: (SortType) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateSortType(it))
    }

    val updateSearchText: (String) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateSearchText(it))
    }


    private fun onSortFilterEvent(sortFilterEvent: SortFilterEvent) {
        when (sortFilterEvent) {
            SortFilterEvent.UpdateAllLabelFilter -> {
                labelFilterType.value = LabelFilterType.All
            }

            SortFilterEvent.UpdateAllWeekdayFilter -> {
                weekdayFilterType.value = WeekdayFilterType.All
            }

            is SortFilterEvent.UpdateLabelFilter -> {
                labelFilterType.value =
                    if (sortFilterEvent.labelSet.size == Label.entries.size)
                        LabelFilterType.All
                    else LabelFilterType.Label(labelSet = sortFilterEvent.labelSet)
            }

            is SortFilterEvent.UpdateSearchText -> {
                searchTextFilter.value = sortFilterEvent.searchText
            }

            is SortFilterEvent.UpdateSortType -> {
                sortType.value = sortFilterEvent.sortType
            }

            is SortFilterEvent.UpdateWeekdayFilter -> {
                weekdayFilterType.value = if (sortFilterEvent.weekdaySet.size == WEEKDAY_SIZE)
                    WeekdayFilterType.All
                else WeekdayFilterType.Weekday(weekdaySet = sortFilterEvent.weekdaySet)
            }
        }
    }
}
