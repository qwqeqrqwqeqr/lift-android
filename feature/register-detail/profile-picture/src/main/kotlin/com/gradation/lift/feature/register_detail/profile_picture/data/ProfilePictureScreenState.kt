package com.gradation.lift.feature.register_detail.profile_picture.data

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Composable
fun rememberProfilePictureScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
): ProfilePictureScreenState =
    remember(snackbarHostState) {
        ProfilePictureScreenState(snackbarHostState)
    }

@Stable
class ProfilePictureScreenState(
    val snackbarHostState: SnackbarHostState,
) {

}

