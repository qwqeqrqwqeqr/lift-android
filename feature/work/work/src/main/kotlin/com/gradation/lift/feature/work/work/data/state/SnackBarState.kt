package com.gradation.lift.feature.work.work.data.state

sealed interface SnackBarState {
    data class Success(val message:String) : SnackBarState
    data object None : SnackBarState
}