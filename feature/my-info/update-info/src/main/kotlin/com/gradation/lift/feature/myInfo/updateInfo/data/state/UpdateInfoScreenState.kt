package com.gradation.lift.feature.myInfo.updateInfo.data.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberUpdateInfoScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    focusManager: FocusManager = LocalFocusManager.current,
): UpdateInfoScreenState =
    remember(snackbarHostState, focusManager) {
        UpdateInfoScreenState(
            snackbarHostState,
            focusManager
        )
    }

data class UpdateInfoScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
)

