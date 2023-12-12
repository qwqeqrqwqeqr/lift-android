package com.gradation.lift.feature.work.complete.data.event

sealed interface HistoryInfoEvent{

    data class UpdateScore(val score:Int): HistoryInfoEvent
    data class UpdateComment(val comment:String): HistoryInfoEvent
}