package com.gradation.lift.feature.history.updateInfo.data.state

sealed interface UpdateHistoryInfoState {

    data object Success : UpdateHistoryInfoState
    data class Fail(val message: String) : UpdateHistoryInfoState
    data object None : UpdateHistoryInfoState

}
