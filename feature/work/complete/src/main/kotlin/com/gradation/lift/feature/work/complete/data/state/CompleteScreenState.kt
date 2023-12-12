package com.gradation.lift.feature.work.complete.data.state

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager


@Composable
internal fun rememberCompleteScreenState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    focusManager: FocusManager = LocalFocusManager.current,
    scrollState: ScrollState = rememberScrollState(),
):CompleteScreenState = remember(snackbarHostState,focusManager,scrollState) {
    CompleteScreenState(snackbarHostState,focusManager,scrollState)
}
@Stable
internal class CompleteScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
    val scrollState: ScrollState,
) {
}


