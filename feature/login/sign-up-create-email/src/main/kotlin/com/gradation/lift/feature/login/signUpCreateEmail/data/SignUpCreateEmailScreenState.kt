package com.gradation.lift.feature.login.signUpCreateEmail.data

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberSignUpCreateEmailScreenState(
    snackbarHostState: SnackbarHostState = remember{SnackbarHostState()},
    focusManager: FocusManager = LocalFocusManager.current,
): SignUpCreateEmailScreenState =
    remember(snackbarHostState, focusManager) {
        SignUpCreateEmailScreenState(
            snackbarHostState, focusManager
        )
    }


data class SignUpCreateEmailScreenState(
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

