package com.gradation.lift.feature.create_routine.find_work_category.data.state

import kotlinx.coroutines.flow.MutableStateFlow

data class WorkPartFilterState(
    val workPartFilter: MutableStateFlow<String> = MutableStateFlow(FILTER_ALL),
) {
    fun updateSelectedWorkPartFilter(): (String) -> Unit = {
        workPartFilter.value = it
    }
    companion object{
        const val FILTER_ALL = "전체"
    }
}