package com.gradation.lift.feature.history.history.data

sealed interface HistoryUiState{

    object Success : HistoryUiState
    object None : HistoryUiState
    object Empty : HistoryUiState
}