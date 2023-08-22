package com.gradation.lift.feature.work.work.data.state

interface WorkDialogUiState {

    object SuspendDialogUi : WorkDialogUiState
    object CompleteDialogUi : WorkDialogUiState
    object AutoCompleteDialogUi : WorkDialogUiState
    object None : WorkDialogUiState
}