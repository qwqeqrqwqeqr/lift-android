package com.gradation.lift.feature.login.signIn.data.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberSignInScreenState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    focusManager: FocusManager = LocalFocusManager.current,
): SignInScreenState =
    remember(snackbarHostState, focusManager) {
        SignInScreenState(snackbarHostState, focusManager)
    }


data class SignInScreenState(
   val snackbarHostState: SnackbarHostState,
   val  focusManager: FocusManager,
)