package com.gradation.lift.feature.workReady.routineSelection.data.state

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
internal fun rememberRoutineListScreen(
    lazyListState: LazyListState = rememberLazyListState(),
    focusManager: FocusManager = LocalFocusManager.current
): RoutineListScreenState {
    return remember(lazyListState) {
        RoutineListScreenState(lazyListState,focusManager)
    }
}

@Stable
internal class RoutineListScreenState(
    val lazyListState: LazyListState,
    val focusManager: FocusManager
) {
    var sortTypeBottomSheetView: Boolean by mutableStateOf(false)
    val updateSortTypeBottomSheetView: (Boolean) -> Unit =
        { sortTypeBottomSheetView = it }

    var weekdayFilterTypeBottomSheetView: Boolean by mutableStateOf(false)
    val updateWeekdayFilterTypeBottomSheetView: (Boolean) -> Unit =
        { weekdayFilterTypeBottomSheetView = it }

    var labelFilterTypeBottomSheetView: Boolean by mutableStateOf(false)
    val updateLabelFilterTypeBottomSheetView: (Boolean) -> Unit =
        { labelFilterTypeBottomSheetView = it }


    var searchSortFilterView: Boolean by mutableStateOf(true)
    val updateSearchSortFilterView: (Boolean) -> Unit =
        { searchSortFilterView = it }

}
