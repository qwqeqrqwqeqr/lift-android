package com.gradation.lift.feature.dailyLog.dailyLog.data.state

import com.gradation.lift.model.model.history.History

interface HistoryUiState {

    data class Success(val historyList: List<History>) : HistoryUiState
    data class Fail(val message: String) : HistoryUiState
    object None : HistoryUiState
    object Empty : HistoryUiState
}