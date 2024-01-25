package com.gradation.lift.feature.work.work.data.state

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.gradation.lift.feature.work.common.data.WorkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberWorkScreenState(
    workState: WorkState,
    pagerState: PagerState = rememberPagerState(
        initialPage = 0,
        pageCount = workState.getWorkRoutineListSize,
    ),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
): WorkScreenState {
    return remember { WorkScreenState(pagerState, snackbarHostState) }
}

@OptIn(ExperimentalFoundationApi::class)
class WorkScreenState(
    val pagerState: PagerState,
    val snackbarHostState: SnackbarHostState,
) {

    val workScreenUiState: MutableStateFlow<WorkScreenUiState> =
        MutableStateFlow(WorkScreenUiState.WorkScreenUi)

    val workDialogUiState: MutableStateFlow<WorkDialogUiState> =
        MutableStateFlow(WorkDialogUiState.None)

    val autoCompleteState: MutableStateFlow<Boolean> = MutableStateFlow(true)


    var snackBarState: SnackBarState by mutableStateOf(SnackBarState.None)
    val updateSnackBarState: (SnackBarState) -> Unit = { snackBarState = it }

    val updateWorkScreenState: (WorkScreenUiState) -> Unit = { state ->
        when (workScreenUiState.value) {
            WorkScreenUiState.ListScreenUi(true) -> {
                workScreenUiState.update { WorkScreenUiState.WorkScreenUi }
            }

            WorkScreenUiState.ListScreenUi(false) -> {
                workScreenUiState.update { WorkScreenUiState.RestScreenUi }
            }

            else -> {
                workScreenUiState.update { state }
            }
        }
    }


    val updateWorkDialogState: (WorkDialogUiState) -> Unit = {
        workDialogUiState.value = it
    }

    val offAutoCompleteState: () -> Unit = {
        autoCompleteState.value = false
    }


}