package com.gradation.lift.feature.login.verifyEmail.data

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberVerifyEmailScreenState(
    snackbarHostState: SnackbarHostState = remember{SnackbarHostState()},
    focusManager: FocusManager = LocalFocusManager.current,
): VerifyEmailScreenState =
    remember(snackbarHostState, focusManager) {
        VerifyEmailScreenState(
            snackbarHostState, focusManager
        )
    }


data class VerifyEmailScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
) {
    var authenticationCodeTextFieldView: Boolean by mutableStateOf(false)
    var authenticationButtonView: Boolean by mutableStateOf(false)
    var updateAuthenticationCodeTextFieldView: (Boolean) -> Unit =
        { authenticationCodeTextFieldView = it }
    var updateAuthenticationButtonView: (Boolean) -> Unit =
        { authenticationButtonView = it }
}

