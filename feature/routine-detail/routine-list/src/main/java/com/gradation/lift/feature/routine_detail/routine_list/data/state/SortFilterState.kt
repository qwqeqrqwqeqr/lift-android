package com.gradation.lift.feature.routine_detail.routine_list.data.state

import com.gradation.lift.feature.routine_detail.routine_list.data.event.SortFilterEvent
import com.gradation.lift.feature.routine_detail.routine_list.data.model.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.Weekday.Companion.WEEKDAY_SIZE
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
data class SortFilterState(
    var labelFilterType: MutableStateFlow<LabelFilterType> = MutableStateFlow(LabelFilterType.All),
    var weekdayFilterType: MutableStateFlow<WeekdayFilterType> = MutableStateFlow(WeekdayFilterType.All),
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

    val getWeekdayFilterTypeNameList: List<String> = when (val type = weekdayFilterType.value) {
        WeekdayFilterType.All -> emptyList()
        is WeekdayFilterType.Weekday -> type.weekdaySet.sortedBy { it.getWeekdayNumber() }
            .map { it.getWeekdayName() }
    }
    val getLabelFilterTypeIdList: List<Int> = when (val type = labelFilterType.value) {
        LabelFilterType.All -> emptyList()
        is LabelFilterType.Label -> type.labelSet.sortedBy { it.id }.map { it.id }
    }

    val getSortTypeName: String = when (val type = sortType.value) {
        SortType.Count -> "사용순"
        SortType.Name -> "기본(이름순)"
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

            is SortFilterEvent.UpdateSearchFilterText -> {
                searchFilterText.value = sortFilterEvent.searchFilterText
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
