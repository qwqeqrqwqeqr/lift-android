package com.gradation.lift.feature.createRoutine.routineSet.data.state

/**
 * [CreateRoutineState]
 * 루틴 생성에 관련한 상태
 * [Success] 상태일 경우 성공적으로 업데이트를 완료한 것이며,
 * [Fail] 상태일 경우 업데이트 반영에 실패한 것을 의미함
 * 초기값은 [None]으로 설정되어 있음
 * @since 2023-12-10 13:57:53
 */
sealed interface CreateRoutineState {


    data object Success : CreateRoutineState
    data class Fail(val message: String) : CreateRoutineState
    data object None : CreateRoutineState
}

