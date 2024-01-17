package com.gradation.lift.feature.login.resetPassword.data

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberResetPasswordScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    focusManager: FocusManager = LocalFocusManager.current,
): ResetPasswordScreenState =
    remember(snackbarHostState, focusManager) {
        ResetPasswordScreenState(
            snackbarHostState, focusManager
        )
    }


data class ResetPasswordScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
) {
    var cancelDialogView: Boolean by mutableStateOf(false)
    var completeDialogView: Boolean by mutableStateOf(false)

    var updateCancelDialogView: (Boolean) -> Unit =
        { cancelDialogView = it }

    var updateCompleteDialogView: (Boolean) -> Unit =
    { completeDialogView = it }
}
