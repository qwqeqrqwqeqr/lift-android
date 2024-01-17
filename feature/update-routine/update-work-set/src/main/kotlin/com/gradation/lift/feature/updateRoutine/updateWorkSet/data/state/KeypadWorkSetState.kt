package com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state

sealed interface KeypadWorkSetState {
    data object None : KeypadWorkSetState
    data object Weight : KeypadWorkSetState
    data object Repetition : KeypadWorkSetState

}