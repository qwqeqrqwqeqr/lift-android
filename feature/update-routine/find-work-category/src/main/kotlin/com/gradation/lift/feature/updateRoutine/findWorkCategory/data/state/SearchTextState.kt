package com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state

import kotlinx.coroutines.flow.MutableStateFlow
/**
 * [SearchTextState]
 * 검색어 상태
 * @since 2023-09-13 17:47:24
 */
data class SearchTextState(
    val searchText: MutableStateFlow<String> = MutableStateFlow("")
) {
    fun updateSearchText(): (String) -> Unit = {
        searchText.value = it
    }
}