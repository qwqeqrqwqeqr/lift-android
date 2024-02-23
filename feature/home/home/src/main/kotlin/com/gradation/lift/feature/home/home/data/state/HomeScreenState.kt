package com.gradation.lift.feature.home.home.data.state

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
import androidx.compose.ui.platform.LocalContext
import com.gradation.lift.feature.home.home.data.model.bannerList
import com.gradation.lift.ui.provider.LocalWarnSnackbarHostState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberHomeScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
    pagerState: PagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { bannerList.size }
    ),
    snackbarHostState: SnackbarHostState = LocalWarnSnackbarHostState.current,
    terminateWaitTime: MutableState<Long> = remember { mutableLongStateOf(0L) },
    context: Context = LocalContext.current,
): HomeScreenState {
    return remember(lazyListState, pagerState, snackbarHostState, terminateWaitTime, context) {
        HomeScreenState(lazyListState, pagerState, snackbarHostState, terminateWaitTime, context)
    }
}

data class HomeScreenState @OptIn(ExperimentalFoundationApi::class) constructor(
    val lazyListState: LazyListState,
    val pagerState: PagerState,
    val snackbarHostState: SnackbarHostState,
    val terminateWaitTime: MutableState<Long>,
    val context: Context,
) {

    var workBottomSheetView: Boolean by mutableStateOf(false)
    val updateWorkBottomSheetView: (Boolean) -> Unit =
        { workBottomSheetView = it }
}

