package com.gradation.lift.myInfo.updateProfilePicture.data.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberUpdateProfilePictureScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
): UpdateProfilePictureScreenState =
    remember(snackbarHostState) {
        UpdateProfilePictureScreenState(
            snackbarHostState
        )
    }

data class UpdateProfilePictureScreenState(
    val snackbarHostState: SnackbarHostState,
) {
}

