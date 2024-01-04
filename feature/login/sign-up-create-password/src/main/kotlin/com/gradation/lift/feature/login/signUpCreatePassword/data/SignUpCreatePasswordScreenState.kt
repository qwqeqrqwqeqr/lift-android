package com.gradation.lift.feature.login.signUpCreatePassword.data

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberSignUpCreatePasswordScreenState(
    snackbarHostState: SnackbarHostState = remember{ SnackbarHostState() },
    focusManager: FocusManager = LocalFocusManager.current,
): SignUpCreatePasswordScreenState =
    remember(snackbarHostState, focusManager) {
        SignUpCreatePasswordScreenState(
            snackbarHostState, focusManager
        )
    }


data class SignUpCreatePasswordScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
) {
    var cancelDialogView: Boolean by mutableStateOf(false)
    var updateCancelDialogView: (Boolean) -> Unit =
        { cancelDialogView = it }
}
