package com.gradation.lift.feature.home.home.data.state

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.gradation.lift.feature.home.home.data.model.bannerList
import com.gradation.lift.ui.provider.LocalInfoSnackbarHostState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberHomeScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
    pagerState: PagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { bannerList.size }
    ),
    snackbarHostState: SnackbarHostState = LocalInfoSnackbarHostState.current,
): HomeScreenState {
    return remember(lazyListState, pagerState, snackbarHostState) {
        HomeScreenState(lazyListState, pagerState, snackbarHostState)
    }
}

data class HomeScreenState @OptIn(ExperimentalFoundationApi::class) constructor(
    val lazyListState: LazyListState,
    val pagerState: PagerState,
    val snackbarHostState: SnackbarHostState,
) {

    var workBottomSheetView: Boolean by mutableStateOf(false)
    val updateWorkBottomSheetView: (Boolean) -> Unit =
        { workBottomSheetView = it }
}

