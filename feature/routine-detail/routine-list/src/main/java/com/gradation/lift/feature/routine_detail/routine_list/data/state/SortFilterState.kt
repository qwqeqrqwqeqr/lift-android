package com.gradation.lift.feature.routine_detail.routine_list.data.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
 * [labelFilterType] 라벨 필터
 * [weekdayFilterType] 요일 필터
 * [searchTextFilter] 검색어 필터
 * [sortType] 정렬 방식
 * @since 2023-11-18 17:25:09
 */
class SortFilterState{
    var labelFilterType: LabelFilterType by mutableStateOf(LabelFilterType.All)
    var weekdayFilterType: WeekdayFilterType by mutableStateOf(WeekdayFilterType.All)
    var searchTextFilter: String by mutableStateOf("")
    var sortType: SortType by mutableStateOf(SortType.Name)

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
                labelFilterType = LabelFilterType.All
            }

            SortFilterEvent.UpdateAllWeekdayFilter -> {
                weekdayFilterType = WeekdayFilterType.All
            }

            is SortFilterEvent.UpdateLabelFilter -> {
                labelFilterType =
                    if (sortFilterEvent.labelSet.size == Label.entries.size)
                        LabelFilterType.All
                    else LabelFilterType.Label(labelSet = sortFilterEvent.labelSet)
            }

            is SortFilterEvent.UpdateSearchText -> {
                searchTextFilter = sortFilterEvent.searchText
            }

            is SortFilterEvent.UpdateSortType -> {
                sortType = sortFilterEvent.sortType
            }

            is SortFilterEvent.UpdateWeekdayFilter -> {
                weekdayFilterType = if (sortFilterEvent.weekdaySet.size == WEEKDAY_SIZE)
                    WeekdayFilterType.All
                else WeekdayFilterType.Weekday(weekdaySet = sortFilterEvent.weekdaySet)
            }
        }
    }
}
