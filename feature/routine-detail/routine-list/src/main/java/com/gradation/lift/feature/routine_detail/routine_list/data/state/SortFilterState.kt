package com.gradation.lift.feature.routine_detail.routine_list.data.state

/**
 * [SortFilterState]
 * 루틴을 조회할 때에 대한 정렬 필터를 모아둔 상태필
 * @param labelFilterType 라벨 필터
 * @param weekdayFilterType 요일 필터
 * @param searchTextFilter 검색어 필터
 * @param sortType 정렬 방식
 * @since 2023-11-18 17:25:09
 */
sealed class SortFilterState(
    val labelFilterType: LabelFilterType,
    val weekdayFilterType: WeekdayFilterType,
    val searchTextFilter: String,
    val sortType: SortType
) {
    class Initial(
        labelFilterType: LabelFilterType = LabelFilterType.All,
        weekdayFilterType: WeekdayFilterType = WeekdayFilterType.All,
        searchTextFilter: String = "",
        sortType: SortType = SortType.Name
    ) : SortFilterState(labelFilterType, weekdayFilterType, searchTextFilter, sortType)


    class Update(
        labelFilterType: LabelFilterType,
        weekdayFilterType: WeekdayFilterType,
        searchTextFilter: String,
        sortType: SortType
    ) : SortFilterState(labelFilterType, weekdayFilterType, searchTextFilter, sortType)


}
