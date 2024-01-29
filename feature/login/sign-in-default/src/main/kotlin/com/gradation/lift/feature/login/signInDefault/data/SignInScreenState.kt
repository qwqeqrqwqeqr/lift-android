package com.gradation.lift.feature.login.signInDefault.data

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberSignInScreenState(
    snackbarHostState: SnackbarHostState =  remember {SnackbarHostState()},
    focusManager: FocusManager = LocalFocusManager.current,
): SignInScreenState =
    remember(snackbarHostState,focusManager) {
        SignInScreenState(snackbarHostState,focusManager)
    }


data class SignInScreenState(
    val snackbarHostState: SnackbarHostState,
    val focusManager: FocusManager
) {
    var email : String by mutableStateOf("")
    var password : String by mutableStateOf("")

    val updateEmail: (String) -> Unit ={ email = it }
    val clearEmail: () -> Unit ={ email = "" }
    val updatePassword: (String) -> Unit ={ password = it }
    val clearPassword: () -> Unit ={ password = "" }

}

