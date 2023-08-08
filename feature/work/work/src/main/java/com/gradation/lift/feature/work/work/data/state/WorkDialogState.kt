package com.gradation.lift.feature.work.work.data.state

interface WorkDialogState {

    object SuspendDialog : WorkDialogState
    object CompleteDialog : WorkDialogState
    object AutoCompleteDialog : WorkDialogState
    object None : WorkDialogState
}