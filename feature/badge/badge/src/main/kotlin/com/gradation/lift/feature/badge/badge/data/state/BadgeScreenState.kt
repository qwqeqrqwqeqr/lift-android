package com.gradation.lift.feature.badge.badge.data.state

import android.content.Context
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.gradation.lift.feature.badge.badge.data.model.BadgeState
import com.gradation.lift.ui.provider.LocalAppScope
import com.gradation.lift.ui.provider.LocalWarnSnackbarHostState
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberBadgeScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
    initialPage: Int,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    appSnackbarHostState: SnackbarHostState = LocalWarnSnackbarHostState.current,
    appCoroutineScope: CoroutineScope = LocalAppScope.current,
    focusManager: FocusManager = LocalFocusManager.current,
    context: Context = LocalContext.current,
): BadgeScreenState {


    return remember(
        lazyListState,
        initialPage,
        snackbarHostState,
        appSnackbarHostState,
        appCoroutineScope,
        focusManager,
        context,
    ) {
        BadgeScreenState(
            lazyListState,
            initialPage,
            snackbarHostState,
            appSnackbarHostState,
            appCoroutineScope,
            focusManager,
            context,
        )
    }
}

data class BadgeScreenState(
    val lazyListState: LazyListState,
    val initialPage: Int,
    val snackbarHostState: SnackbarHostState,
    val appSnackbarHostState: SnackbarHostState,
    val appCoroutineScope: CoroutineScope,
    val focusManager: FocusManager,
    val context: Context,
) {
    val selectedPage: MutableState<Int> = mutableIntStateOf(initialPage)

    val filterBottomSheetView: MutableState<Boolean> = mutableStateOf(false)
    val sortBottomSheetView: MutableState<Boolean> = mutableStateOf(false)
    val badgeDetailDialogView: MutableState<Pair<Boolean, BadgeState?>> =
        mutableStateOf(Pair(false, null))
    val updateCancelDialog: MutableState<Boolean> = mutableStateOf(false)

    val updateFilterBottomSheetView: (Boolean) -> Unit = { filterBottomSheetView.value = it }
    val updateSortBottomSheetView: (Boolean) -> Unit = { sortBottomSheetView.value = it }
    val updateBadgeDetailDialogView: (Pair<Boolean, BadgeState?>) -> Unit =
        { badgeDetailDialogView.value = it }
    val updateUpdateCancelDialog: (Boolean) -> Unit = { updateCancelDialog.value = it }

    val updateSelectedPage: (Int) -> Unit = { selectedPage.value = it }

}


