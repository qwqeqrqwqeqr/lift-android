package com.gradation.lift.feature.history.history.data.state

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberHistoryScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scrollState: ScrollState = rememberScrollState(),
): HistoryScreenState =
    remember(snackbarHostState, scrollState) {
        HistoryScreenState(snackbarHostState, scrollState)
    }


data class HistoryScreenState(
    val snackbarHostState: SnackbarHostState,
    val scrollState: ScrollState,
) {
    var workBottomSheetView: Boolean by mutableStateOf(false)
    val updateWorkBottomSheetView: (Boolean) -> Unit =
        { workBottomSheetView = it }

    var selectedIndex: Int by mutableIntStateOf(0)
    val updateSelectedIndex: (Int) -> Unit = { selectedIndex = it }
}

