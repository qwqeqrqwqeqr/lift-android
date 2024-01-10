package com.gradation.lift.createRoutine.createWorkSet.data.state

sealed interface KeypadWorkSetState {
    data object None : KeypadWorkSetState
    data object Weight : KeypadWorkSetState
    data object Repetition : KeypadWorkSetState

}