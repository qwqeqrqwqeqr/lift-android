package com.gradation.lift.feature.work.work.data


/**
 * [ListScreen]
 * true -> [WorkScreen] false -> [RestScreen]
 * [WorkScreen]
 * true -> [RestScreen] false -> [ListScreen]
 * [RestScreen]
 * true -> [WorkScreen] false -> [ListScreen]
 */
sealed interface WorkScreenState {

    object RestScreen : WorkScreenState
    object WorkScreen : WorkScreenState
    data class ListScreen(val state: Boolean) : WorkScreenState




}