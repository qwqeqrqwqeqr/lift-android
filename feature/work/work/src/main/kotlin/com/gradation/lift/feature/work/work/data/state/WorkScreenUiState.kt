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

    data object RestScreenUi : WorkScreenUiState
    data object WorkScreenUi : WorkScreenUiState
    data class ListScreenUi(val state: Boolean) : WorkScreenUiState

}