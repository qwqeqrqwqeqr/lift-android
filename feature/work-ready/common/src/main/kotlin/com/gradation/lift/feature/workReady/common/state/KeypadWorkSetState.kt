package com.gradation.lift.feature.workReady.common.state

sealed interface KeypadWorkSetState {
    data object None : KeypadWorkSetState
    data object Weight : KeypadWorkSetState
    data object Repetition : KeypadWorkSetState

}