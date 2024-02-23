package com.gradation.lift.feature.analytics.analytics.data.state

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberAnalyticsScreenState(
    today: kotlinx.datetime.LocalDate,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    lazyListState: LazyListState = rememberLazyListState(),
    hapticFeedbackType: HapticFeedback = LocalHapticFeedback.current,
    pagerState: PagerState = rememberPagerState(
        initialPage = START_DATE.until(today.toJavaLocalDate(), ChronoUnit.MONTHS).toInt(),
        pageCount = { START_DATE.until(END_DATE, ChronoUnit.MONTHS).toInt() },
    ),
    terminateWaitTime: MutableState<Long> = remember { mutableLongStateOf(0L) },
    context: Context = LocalContext.current,
): AnalyticsScreenState {
    return remember(snackbarHostState, lazyListState, pagerState, terminateWaitTime, context) {
        AnalyticsScreenState(
            snackbarHostState,
            lazyListState,
            hapticFeedbackType,
            pagerState,
            terminateWaitTime,
            context
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
data class AnalyticsScreenState(
    val snackbarHostState: SnackbarHostState,
    val lazyListState: LazyListState,
    val hapticFeedbackType: HapticFeedback,
    val pagerState: PagerState,
    val terminateWaitTime: MutableState<Long>,
    val context: Context,
) {
    var workBottomSheetView: Boolean by mutableStateOf(false)
    val updateWorkBottomSheetView: (Boolean) -> Unit =
        { workBottomSheetView = it }

    var datePickerBottomSheetView: Boolean by mutableStateOf(false)
    val updateDatePickerBottomSheetView: (Boolean) -> Unit =
        { datePickerBottomSheetView = it }

    val yearRange: IntRange = (START_DATE.year..END_DATE.year)
}


val START_DATE: LocalDate = LocalDate.of(2000, 1, 1)
val END_DATE: LocalDate = LocalDate.of(2099, 12, 31)
