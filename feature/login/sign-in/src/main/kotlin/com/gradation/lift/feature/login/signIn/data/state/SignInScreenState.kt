package com.gradation.lift.feature.login.signIn.data.state

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberSignInScreenState(
    snackbarHostState: SnackbarHostState =  remember {SnackbarHostState()},
    context: Context = LocalContext.current,
): SignInScreenState =
    remember(snackbarHostState, context) {
        SignInScreenState(snackbarHostState, context)
    }


data class SignInScreenState(
    val snackbarHostState: SnackbarHostState,
    val context: Context,
)

