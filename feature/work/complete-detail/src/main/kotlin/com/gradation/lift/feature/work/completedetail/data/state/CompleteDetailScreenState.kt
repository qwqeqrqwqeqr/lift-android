package com.gradation.lift.feature.work.completedetail.data.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager


@Composable
internal fun rememberCompleteDetailScreenState(
    snackbarHostState: SnackbarHostState =  remember {SnackbarHostState()},
    focusManager: FocusManager = LocalFocusManager.current
): CompleteDetailScreenState = remember(snackbarHostState,focusManager) {
    CompleteDetailScreenState(snackbarHostState,focusManager)
}
@Stable
internal class CompleteDetailScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager :FocusManager
) {
}


