package com.gradation.lift.feature.createRoutine.findWorkCategory.data.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
internal fun rememberFindWorkCategoryScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
    focusManager: FocusManager = LocalFocusManager.current
): FindWorkCategoryScreenState = remember(lazyListState) {
    FindWorkCategoryScreenState(lazyListState,focusManager)
}


@Stable
internal class FindWorkCategoryScreenState(
    val lazyListState: LazyListState,
    val focusManager: FocusManager
) {
    var searchSortFilterView: Boolean by mutableStateOf(true)
    val updateSearchSortFilterView: (Boolean) -> Unit =
        { searchSortFilterView = it }
}