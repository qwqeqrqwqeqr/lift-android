package com.gradation.lift.feature.create_routine.find_work_category.data.state

import kotlinx.coroutines.flow.MutableStateFlow

data class SearchTextState(
    val searchText: MutableStateFlow<String> = MutableStateFlow("")
) {
    fun updateSearchText(): (String) -> Unit = {
        searchText.value = it
    }
}