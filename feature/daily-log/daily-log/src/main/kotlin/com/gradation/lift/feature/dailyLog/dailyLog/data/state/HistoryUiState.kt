package com.gradation.lift.feature.dailyLog.dailyLog.data.state

import com.gradation.lift.model.model.history.History

sealed interface HistoryUiState {

    data class Success(val historyList: List<History>) : HistoryUiState
    data class Fail(val message: String) : HistoryUiState
    data object Loading : HistoryUiState
    data object Empty : HistoryUiState
}