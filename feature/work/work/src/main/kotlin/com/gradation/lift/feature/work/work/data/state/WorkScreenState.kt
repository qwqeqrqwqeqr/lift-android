package com.gradation.lift.feature.work.work.data.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


@Composable
fun rememberWorkScreenState(): WorkScreenState = remember()
    {
        WorkScreenState()
    }


class WorkScreenState(

) {

    val workScreenUiState: MutableStateFlow<WorkScreenUiState> =
        MutableStateFlow(WorkScreenUiState.WorkScreenUi)

    val workDialogUiState: MutableStateFlow<WorkDialogUiState> =
        MutableStateFlow(WorkDialogUiState.None)

    val autoCompleteState: MutableStateFlow<Boolean> = MutableStateFlow(true)



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