package com.gradation.lift.feature.home.home.data.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberHomeScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
): HomeScreenState {


    return remember(lazyListState,) {
        HomeScreenState(lazyListState)
    }


}

data class HomeScreenState(
    val lazyListState: LazyListState,

) {
    var visibleBannerView: Boolean by mutableStateOf(true)
    val updateVisibleBannerView: (Boolean) -> Unit = { visibleBannerView = it }


    var workBottomSheetView: Boolean by mutableStateOf(false)
    val updateWorkBottomSheetView: (Boolean) -> Unit =
        { workBottomSheetView = it }
}

