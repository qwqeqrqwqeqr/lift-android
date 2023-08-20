package com.gradation.lift.feature.create_routine.routine_set.data.state



sealed interface CreateRoutineState {
    object Success : CreateRoutineState
    object None : CreateRoutineState
    data class Fail(val message:String) : CreateRoutineState
}
¬