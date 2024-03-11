package com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.gradation.lift.ui.provider.LocalAppScope
import com.gradation.lift.ui.provider.LocalInfoSnackbarHostState
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberRoutineScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
    focusManager: FocusManager = LocalFocusManager.current,
    appScope: CoroutineScope = LocalAppScope.current,
    snackbarHostState: SnackbarHostState = LocalInfoSnackbarHostState.current,
): RoutineScreenState {
    return remember(lazyListState, focusManager, appScope, snackbarHostState) {
        RoutineScreenState(lazyListState, focusManager, appScope, snackbarHostState)
    }
}

internal class RoutineScreenState(
    val lazyListState: LazyListState,
    val focusManager: FocusManager,
    val appScope: CoroutineScope,
    val snackbarHostState: SnackbarHostState,
) {
    val tabTitleList = listOf<String>("루틴제작", "운동가이드")

    val selectedTabIndex: MutableState<Int> = mutableIntStateOf(CREATE_ROUTINE)
    val updateSelectedTabIndex: (Int) -> Unit = { selectedTabIndex.value = it }


    companion object {
        const val CREATE_ROUTINE = 0
        const val EXERCISE_GUIDE = 1
    }
}

