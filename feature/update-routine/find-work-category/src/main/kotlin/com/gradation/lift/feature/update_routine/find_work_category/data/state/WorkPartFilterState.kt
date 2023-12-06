package com.gradation.lift.feature.update_routine.find_work_category.data.state

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * [WorkPartFilterState]
 * 운동부위 필터 상태
 * @since 2023-09-13 17:46:15
 */
data class WorkPartFilterState(
    val workPartFilter: MutableStateFlow<String> = MutableStateFlow(FILTER_ALL),
) {
    fun updateWorkPartFilter(): (String) -> Unit = {
        workPartFilter.value = it
    }
    companion object{
        const val FILTER_ALL = "전체"
    }
}