package com.gradation.lift.feature.work.createWorkSet.data.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Composable
internal fun rememberRoutineScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
): RoutineScreenState {
    return remember(lazyListState) {
        RoutineScreenState(lazyListState)
    }
}

@Stable
internal class RoutineScreenState(
    val lazyListState: LazyListState,
)

