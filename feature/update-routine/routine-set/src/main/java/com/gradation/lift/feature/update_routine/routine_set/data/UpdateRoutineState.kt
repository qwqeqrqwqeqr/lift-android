package com.gradation.lift.feature.update_routine.routine_set.data

/**
 * [UpdateRoutineState]
 * 루틴 업데이트(삭제 또는 수정)에 관련한 상태
 * [Success] 상태일 경우 성공적으로 업데이트를 완료한 것이며,
 * [Fail] 상태일 경우 업데이트 반영에 실패한 것을 의미함
 * 초기값은 [None]으로 설정되어 있음
 * @since 2023-09-07 09:01:58
 */
interface UpdateRoutineState {
    object Success : UpdateRoutineState
    data class Fail(val message: String) : UpdateRoutineState
    object None : UpdateRoutineState
}

