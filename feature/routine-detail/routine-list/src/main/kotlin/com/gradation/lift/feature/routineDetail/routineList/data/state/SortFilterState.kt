package com.gradation.lift.feature.routineDetail.routineList.data.state

import com.gradation.lift.feature.routineDetail.routineList.data.event.SortFilterEvent
import com.gradation.lift.feature.routineDetail.routineList.data.model.LabelFilterType
import com.gradation.lift.feature.routineDetail.routineList.data.model.SortType
import com.gradation.lift.feature.routineDetail.routineList.data.model.WeekdayFilterType
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.model.model.routine.Label
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * [SortFilterState]
 * 루틴을 조회할 때에 대한 정렬 필터를 모아둔 상태
 * [labelFilterType] 라벨 필터
 * [weekdayFilterType] 요일 필터
 * [searchFilterText] 검색어 필터
 * [sortType] 정렬 방식
 * @since 2023-11-18 17:25:09
 */
internal data class SortFilterState(
    var labelFilterType: MutableStateFlow<LabelFilterType> = MutableStateFlow(LabelFilterType(Label.entries.toSet())),
    var weekdayFilterType: MutableStateFlow<WeekdayFilterType> = MutableStateFlow(
        WeekdayFilterType(
            getWeekdayEntries().toSet()
        )
    ),
    var searchFilterText: MutableStateFlow<String> = MutableStateFlow(""),
    var sortType: MutableStateFlow<SortType> = MutableStateFlow(SortType.Name)
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

    val updateSearchFilterText: (String) -> Unit = {
        onSortFilterEvent(SortFilterEvent.UpdateSearchFilterText(it))
    }


    private fun onSortFilterEvent(sortFilterEvent: SortFilterEvent) {
        when (sortFilterEvent) {
            SortFilterEvent.UpdateAllLabelFilter -> {
                labelFilterType.value = LabelFilterType(Label.entries.toSet())
            }

            SortFilterEvent.UpdateAllWeekdayFilter -> {
                weekdayFilterType.value = WeekdayFilterType(getWeekdayEntries().toSet())
            }

            is SortFilterEvent.UpdateLabelFilter -> {
                labelFilterType.value = LabelFilterType(labelSet = sortFilterEvent.labelSet)
            }

            is SortFilterEvent.UpdateSearchFilterText -> {
                searchFilterText.value = sortFilterEvent.searchFilterText
            }

            is SortFilterEvent.UpdateSortType -> {
                sortType.value = sortFilterEvent.sortType
            }

            is SortFilterEvent.UpdateWeekdayFilter -> {
                weekdayFilterType.value = WeekdayFilterType(weekdaySet = sortFilterEvent.weekdaySet)
            }
        }
    }
}
