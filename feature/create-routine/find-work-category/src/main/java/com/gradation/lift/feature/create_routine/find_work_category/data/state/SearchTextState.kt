package com.gradation.lift.feature.create_routine.find_work_category.data.state

import kotlinx.coroutines.flow.MutableStateFlow
/**
 * [SearchTextState]
 * 검색어 상태
 * @since 2023-08-21 20:13:54
 */
data class SearchTextState(
    val searchText: MutableStateFlow<String> = MutableStateFlow("")
) {
    fun updateSearchText(): (String) -> Unit = {
        searchText.value = it
    }
}