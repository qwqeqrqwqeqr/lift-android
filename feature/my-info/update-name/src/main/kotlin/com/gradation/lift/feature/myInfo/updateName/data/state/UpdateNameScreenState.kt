package com.gradation.lift.feature.myInfo.updateName.data.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberUpdateNameScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    focusManager: FocusManager = LocalFocusManager.current,
): UpdateNameScreenState =
    remember(snackbarHostState, focusManager) {
        UpdateNameScreenState(
            snackbarHostState,
            focusManager
        )
    }

data class UpdateNameScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
) {
}

