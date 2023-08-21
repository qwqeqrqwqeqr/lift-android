package com.gradation.lift.feature.create_routine.find_work_category.data.state

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * [WorkPartFilterState]
 * 운동부위 필터 상태
 * @since 2023-08-21 20:14:01
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