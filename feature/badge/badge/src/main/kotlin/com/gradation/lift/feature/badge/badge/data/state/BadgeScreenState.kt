package com.gradation.lift.feature.badge.badge.data.state

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.gradation.lift.feature.badge.badge.data.model.UserBadge
import com.gradation.lift.ui.provider.LocalWarnSnackbarHostState
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberBadgeScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
    initialPage: Int,
    pagerState: PagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { 2 }
    ),
    snackbarHostState: SnackbarHostState = LocalWarnSnackbarHostState.current,
    context: Context = LocalContext.current,
): BadgeScreenState {
    return remember(lazyListState, pagerState, snackbarHostState, context) {
        BadgeScreenState(
            lazyListState,
            pagerState,
            snackbarHostState,
            context
        )
    }
}

data class BadgeScreenState @OptIn(ExperimentalFoundationApi::class) constructor(
    val lazyListState: LazyListState,
    val pagerState: PagerState,
    val snackbarHostState: SnackbarHostState,
    val context: Context,
) {

    val filterBottomSheetView: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val sortBottomSheetView: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val badgeDetailDialogView: MutableStateFlow<Pair<Boolean, UserBadge?>> =
        MutableStateFlow(Pair(false, null))


    val updateFilterBottomSheetView: (Boolean) -> Unit = { filterBottomSheetView.value = it }
    val updateSortBottomSheetView: (Boolean) -> Unit = { sortBottomSheetView.value = it }
    val updateBadgeDetailDialogView: (Pair<Boolean, UserBadge?>) -> Unit =
        { badgeDetailDialogView.value = it }
}


