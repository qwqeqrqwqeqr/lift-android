package com.gradation.lift.feature.updateRoutine.routineSet.data.state

/**
 * [UpdateRoutineState]
 * 루틴 업데이트(삭제 또는 수정)에 관련한 상태
 * [Success] 상태일 경우 성공적으로 업데이트를 완료한 것이며,
 * [Fail] 상태일 경우 업데이트 반영에 실패한 것을 의미함
 * 초기값은 [None]으로 설정되어 있음
 * @since 2023-12-06 17:39:55
 */
interface UpdateRoutineState {

    /**
     * [Success]
     * @param isDeleted  삭제 여부, false 경우 업데이트 이며, true일 경우 삭제를 의미
     * @since 2023-12-06 17:40:42
     */
    data class Success(val isDeleted: Boolean) : UpdateRoutineState
    data class Fail(val message: String) : UpdateRoutineState
    object None : UpdateRoutineState
}

