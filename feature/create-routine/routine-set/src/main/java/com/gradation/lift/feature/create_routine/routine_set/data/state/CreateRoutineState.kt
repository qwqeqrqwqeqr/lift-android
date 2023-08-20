package com.gradation.lift.feature.create_routine.routine_set.data.state



sealed interface CreateRoutineState {
    object Success : CreateRoutineState
    object Loading : CreateRoutineState
    object Fail : CreateRoutineState
}
¬