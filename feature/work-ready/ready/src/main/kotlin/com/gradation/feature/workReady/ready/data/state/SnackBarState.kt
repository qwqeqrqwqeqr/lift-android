package com.gradation.feature.workReady.ready.data.state

sealed interface SnackBarState {
    data class RemoveUndo(val size:Int) : SnackBarState
    data object CanNotRemove : SnackBarState
    data object None : SnackBarState
}