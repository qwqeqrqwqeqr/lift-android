package com.gradation.lift.feature.history.updateInfo.data.state

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberUpdateInfoScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    focusManager: FocusManager = LocalFocusManager.current,
    scrollState: ScrollState = rememberScrollState(),
): UpdateInfoScreenState =
    remember(snackbarHostState, scrollState) {
        UpdateInfoScreenState(snackbarHostState, focusManager, scrollState)
    }


data class UpdateInfoScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
    val scrollState: ScrollState,
)

