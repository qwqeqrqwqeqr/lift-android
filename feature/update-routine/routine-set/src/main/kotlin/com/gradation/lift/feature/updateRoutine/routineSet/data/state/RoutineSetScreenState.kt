package com.gradation.lift.feature.updateRoutine.routineSet.data.state

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
internal fun rememberRoutineSetScreenState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    focusManager: FocusManager = LocalFocusManager.current,
    lazyListState: LazyListState = rememberLazyListState(),
    scrollState: ScrollState = rememberScrollState(),
): RoutineSetScreenState {
    return remember(snackbarHostState, focusManager, lazyListState) {
        RoutineSetScreenState(
            snackbarHostState,
            focusManager,
            lazyListState,
            scrollState,
        )
    }
}

@Stable
internal class RoutineSetScreenState @OptIn(ExperimentalFoundationApi::class) constructor(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
    val lazyListState: LazyListState,
    val scrollState: ScrollState,
) {
    var deleteDialogView: Boolean by mutableStateOf(false)
    val updateDeleteDialogView: (Boolean) -> Unit =
        { deleteDialogView = it }

    var routineSetView: Boolean by mutableStateOf(true)
    val updateRoutineSetView: (Boolean) -> Unit =
        { routineSetView = it }
}

