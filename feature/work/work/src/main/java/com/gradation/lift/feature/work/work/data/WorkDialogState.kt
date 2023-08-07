package com.gradation.lift.feature.work.work.data

interface WorkDialogState {

    object SuspendDialog : WorkDialogState
    object CompleteDialog : WorkDialogState
    object None : WorkDialogState
}