package com.gradation.lift.feature.work.createWorkSet.data.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
internal fun rememberRoutineScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
    focusManager: FocusManager = LocalFocusManager.current,
): RoutineScreenState {
    return remember(lazyListState, focusManager) {
        RoutineScreenState(lazyListState, focusManager)
    }
}

@Stable
internal class RoutineScreenState(
    val lazyListState: LazyListState,
    val focusManager: FocusManager,
)

