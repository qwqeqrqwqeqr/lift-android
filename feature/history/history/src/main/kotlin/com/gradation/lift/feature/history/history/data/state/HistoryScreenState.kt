package com.gradation.lift.feature.history.history.data.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.platform.LocalHapticFeedback

@Composable
fun rememberHistoryScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    lazyListState: LazyListState = rememberLazyListState(),
    hapticFeedbackType: HapticFeedback = LocalHapticFeedback.current,
): HistoryScreenState =
    remember(snackbarHostState, lazyListState) {
        HistoryScreenState(snackbarHostState, lazyListState, hapticFeedbackType)
    }


data class HistoryScreenState(
    val snackbarHostState: SnackbarHostState,
    val lazyListState: LazyListState,
    val hapticFeedbackType: HapticFeedback,
) {
    var workBottomSheetView: Boolean by mutableStateOf(false)
    val updateWorkBottomSheetView: (Boolean) -> Unit =
        { workBottomSheetView = it }

    var datePickerBottomSheetView: Boolean by mutableStateOf(false)
    val updateDatePickerBottomSheetView: (Boolean) -> Unit =
        { datePickerBottomSheetView = it }

    var selectedIndex: Int by mutableIntStateOf(0)
    val updateSelectedIndex: (Int) -> Unit = { selectedIndex = it }
}

