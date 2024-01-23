package com.gradation.lift.feature.work.work.data.state

sealed interface WorkDialogUiState {

    data object SuspendDialogUi : WorkDialogUiState
    data object CompleteDialogUi : WorkDialogUiState
    data object AutoCompleteDialogUi : WorkDialogUiState
    data object None : WorkDialogUiState
}