package com.gradation.lift.feature.login.signIn.data.state

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberSignInScreenState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    focusManager: FocusManager = LocalFocusManager.current,
    context: Context = LocalContext.current,
): SignInScreenState =
    remember(snackbarHostState, focusManager, context) {
        SignInScreenState(snackbarHostState, focusManager, context)
    }


data class SignInScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
    val context: Context,
)

