package com.gradation.lift.feature.history.updateInfo.data.state

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberUpdateInfoScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scrollState: ScrollState = rememberScrollState(),
): UpdateInfoScreenState =
    remember(snackbarHostState, scrollState) {
        UpdateInfoScreenState(snackbarHostState, scrollState)
    }


data class UpdateInfoScreenState(
    val snackbarHostState: SnackbarHostState,
    val scrollState: ScrollState,
)

