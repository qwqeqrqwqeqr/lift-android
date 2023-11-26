package com.gradation.lift.feature.routine_detail.routine_list.data.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
internal fun rememberRoutineListScreen(): RoutineListScreenState {
    return remember() {
        RoutineListScreenState()
    }
}

@Stable
internal class RoutineListScreenState {
    var sortTypeBottomSheetView: Boolean by mutableStateOf(false)
    val updateSortTypeBottomSheetView: (Boolean) -> Unit =
        { sortTypeBottomSheetView = it }

    var weekdayFilterTypeBottomSheetView: Boolean by mutableStateOf(false)
    val updateWeekdayFilterTypeBottomSheetView: (Boolean) -> Unit =
        { weekdayFilterTypeBottomSheetView = it }

    var labelFilterTypeBottomSheetView: Boolean by mutableStateOf(false)
    val updateLabelFilterTypeBottomSheetView: (Boolean) -> Unit =
        { labelFilterTypeBottomSheetView = it }

}
