package com.gradation.lift.feature.history.history.data.state

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberHistoryScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    context: Context = LocalContext.current,
    scrollState: ScrollState = rememberScrollState(),
): HistoryScreenState =
    remember(snackbarHostState, context, scrollState) {
        HistoryScreenState(snackbarHostState, context, scrollState)
    }


data class HistoryScreenState(
    val snackbarHostState: SnackbarHostState,
    val context: Context,
    val scrollState: ScrollState,
)

