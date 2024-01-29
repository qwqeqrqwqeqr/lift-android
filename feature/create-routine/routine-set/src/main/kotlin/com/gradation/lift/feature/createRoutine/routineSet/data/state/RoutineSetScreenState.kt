package com.gradation.lift.feature.createRoutine.routineSet.data.state

import androidx.compose.foundation.ScrollState
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
    snackbarHostState: SnackbarHostState =  remember {SnackbarHostState()},
    focusManager: FocusManager = LocalFocusManager.current,
    scrollState: ScrollState = rememberScrollState(),
): RoutineSetScreenState {
    return remember(snackbarHostState, focusManager) {
        RoutineSetScreenState(
            snackbarHostState,
            focusManager,
            scrollState,
        )
    }
}

@Stable
internal class RoutineSetScreenState constructor(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
    val scrollState: ScrollState,
) {
    var cancelDialogView: Boolean by mutableStateOf(false)
    val updateCancelDialogView: (Boolean) -> Unit =
        { cancelDialogView = it }

    var completeDialogView: Boolean by mutableStateOf(false)
    val updateCompleteDialogView: (Boolean) -> Unit =
        { completeDialogView = it }
}

