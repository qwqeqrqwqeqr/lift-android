package com.gradation.lift.feature.workReady.routineSelection.data.event

import com.gradation.lift.feature.workReady.routineSelection.data.model.SortType
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.Label

/**
 * [SortFilterEvent]
 * 정렬 및 필터를 업데이트하는 이벤트
 * [UpdateAllWeekdayFilter] 전체 요일을 조회하도록 필터를 업데이트합니다.
 * [UpdateWeekdayFilter] 특정 요일만 조회하도록 필터를 업데이트합니다.
 * [UpdateAllLabelFilter] 전체 라벨을 조회하도록 필터를 업데이트합니다.
 * [UpdateLabelFilter] 특정 라벨만 조회하도록 필터를 업데이트합니다.
 * [UpdateSortType] 정렬 방식을 업데이트합니다.
 * [UpdateSearchFilterText] 검색어를 업데이트 합니다
 * [ClearSearchFilterText] 검색어를 삭제합니다.
 * @since 2024-01-08 19:51:40
 */
internal sealed interface SortFilterEvent {

    data object UpdateAllWeekdayFilter : SortFilterEvent
    data class UpdateWeekdayFilter(val weekdaySet: Set<Weekday>) : SortFilterEvent
    data object UpdateAllLabelFilter : SortFilterEvent
    data class UpdateLabelFilter(val labelSet: Set<Label>) : SortFilterEvent
    data class UpdateSortType(val sortType: SortType) : SortFilterEvent
    data class UpdateSearchFilterText(val searchFilterText: String) : SortFilterEvent
    data object ClearSearchFilterText : SortFilterEvent
}