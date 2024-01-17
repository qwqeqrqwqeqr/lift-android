package com.gradation.feature.workReady.ready.data.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun rememberReadyScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
): ReadyScreenState = remember(snackbarHostState)
{
    ReadyScreenState(snackbarHostState)
}


class ReadyScreenState(
    val snackbarHostState: SnackbarHostState,
) {
    var snackBarState: SnackBarState by mutableStateOf(SnackBarState.None)
    val updateSnackBarState: (SnackBarState)-> Unit = {snackBarState=it}
}