package com.gradation.lift.feature.createRoutine.findWorkCategory.data.state

import com.gradation.lift.feature.createRoutine.findWorkCategory.data.event.FilterEvent
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * [FilterState]
 * 운동을 조회할 때에 대한 필터를 모아둔 상태
 * [workPartFilter] 운동 부위 필터
 * [searchFilterText] 검색어 필터
 * @since 2023-12-07 11:17:38
 */
internal data class FilterState(
    var workPartFilter: MutableStateFlow<Set<WorkPart>> = MutableStateFlow(emptySet()),
    var searchFilterText: MutableStateFlow<String> = MutableStateFlow(""),
) {


    val updateWorkPartFilter: (Set<WorkPart>) -> Unit = {
        onFilterEvent(FilterEvent.UpdateWorkPartFilter(it))
    }
    val updateSearchText: (String) -> Unit = {
        onFilterEvent(FilterEvent.UpdateSearchText(it))
    }
    val clearSearchText: () -> Unit  ={
        onFilterEvent(FilterEvent.ClearSearchText)
    }

    private fun onFilterEvent(filterEvent: FilterEvent) {
        when (filterEvent) {
            is FilterEvent.UpdateSearchText -> {
                searchFilterText.value = filterEvent.searchFilterText
            }

            is FilterEvent.UpdateWorkPartFilter -> {
                workPartFilter.value = filterEvent.workPartFilter
            }
            is FilterEvent.ClearSearchText ->{
                searchFilterText.value = ""
            }
        }
    }


}
