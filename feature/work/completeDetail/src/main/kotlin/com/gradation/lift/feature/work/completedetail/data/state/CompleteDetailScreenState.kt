package com.gradation.lift.feature.work.completedetail.data.state

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember


@Composable
internal fun rememberCompleteDetailScreenState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    scrollState: ScrollState = rememberScrollState(),
): CompleteDetailScreenState = remember(snackbarHostState,scrollState) {
    CompleteDetailScreenState(snackbarHostState,scrollState)
}
@Stable
internal class CompleteDetailScreenState(
    val snackbarHostState: SnackbarHostState,
    val scrollState: ScrollState,
) {
}


