package com.gradation.lift.feature.myInfo.profile.data.state

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberProfileScreenState(
    context: Context = LocalContext.current,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
): ProfileScreenState =
    remember(context, snackbarHostState) {
        ProfileScreenState(
            context,
            snackbarHostState
        )
    }

data class ProfileScreenState(
    val context: Context,
    val snackbarHostState: SnackbarHostState,
) {
    var signOutDialogView: Boolean by mutableStateOf(false)
    val updateSignOutDialogView: (Boolean) -> Unit =
        { signOutDialogView = it }
}

