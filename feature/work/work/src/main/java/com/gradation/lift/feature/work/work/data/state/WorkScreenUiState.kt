package com.gradation.lift.feature.work.work.data.state


/**
 * [ListScreenUi]
 * true -> [WorkScreenUi] false -> [RestScreenUi]
 * [WorkScreenUi]
 * true -> [RestScreenUi] false -> [ListScreenUi]
 * [RestScreenUi]
 * true -> [WorkScreenUi] false -> [ListScreenUi]
 */
sealed interface WorkScreenUiState {

    object RestScreenUi : WorkScreenUiState
    object WorkScreenUi : WorkScreenUiState
    data class ListScreenUi(val state: Boolean) : WorkScreenUiState




}