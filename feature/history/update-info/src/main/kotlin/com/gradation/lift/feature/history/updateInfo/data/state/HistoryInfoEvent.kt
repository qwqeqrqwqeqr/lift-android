package com.gradation.lift.feature.history.updateInfo.data.state

sealed interface HistoryInfoEvent {

    data class UpdateScore(val score: Int) : HistoryInfoEvent
    data class UpdateComment(val comment: String) : HistoryInfoEvent
}