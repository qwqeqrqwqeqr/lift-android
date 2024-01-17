package com.gradation.lift.feature.updateRoutine.createWorkSet.data.state

sealed interface KeypadWorkSetState {
    data object None : KeypadWorkSetState
    data object Weight : KeypadWorkSetState
    data object Repetition : KeypadWorkSetState

}